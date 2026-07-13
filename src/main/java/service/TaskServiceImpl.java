package service;

import dto.TaskRequest;
import dto.TaskResponse;
import exception.NotFoundException;
import mapper.TaskMapper;
import model.Audit;
import model.LogLevel;
import model.Task;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.AuditRepository;
import repository.TaskRepository;

import java.time.LocalDateTime;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;
    private final AuditRepository auditRepository;

    public TaskServiceImpl(TaskRepository repository, TaskMapper mapper, AuditRepository auditRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.auditRepository = auditRepository;
    }

    @Override
    public Mono<TaskResponse> createTask(TaskRequest newTask) {
        Task newTaskEntity = mapper.toEntity(newTask);
        return repository.save(newTaskEntity)
                .flatMap(taskSaved -> {
                    Audit audit = new Audit(LogLevel.INFO, taskSaved.getId(), "Task created " + taskSaved.getDescription());
                    return auditRepository.save(audit).thenReturn(taskSaved);
                })
                .map(taskSaved -> mapper.toResponse(taskSaved));


    }

    @Override
    public Mono<TaskResponse> updateTask(Long id, TaskRequest updateTask) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(() -> new NotFoundException("Task Not Found")))
                .map(taskToUpdate -> {
                    taskToUpdate.setDescription(updateTask.description());
                    taskToUpdate.setPriority(updateTask.priority());
                    taskToUpdate.setStatus(updateTask.status());
                    taskToUpdate.setUpdatedAt(LocalDateTime.now());
                    return taskToUpdate;
                })
                .flatMap(taskSaved -> repository.save(taskSaved))
                .flatMap(taskSaved -> {
                    Audit audit = new Audit(
                            LogLevel.INFO,
                            taskSaved.getId(),
                            "Task updated " + taskSaved.getDescription()
                    );
                    return auditRepository.save(audit)
                            .thenReturn(taskSaved);
                })
                .map(taskSaved -> mapper.toResponse(taskSaved));
    }



    @Override
    public Mono<Void> deleteTask(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(() -> new NotFoundException("Task with id " + id + " not found")))
                .flatMap(taskSaved -> repository.deleteById(id));

    }

    @Override
    public Mono<TaskResponse> getTaskById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(() -> new NotFoundException("Task with id " + id + " not found")))
                .map(mapper::toResponse);
    }

    @Override
    public Flux<TaskResponse> getAllTasks() {
        return repository.findAll()
                .switchIfEmpty(Mono.error(() -> new NotFoundException("No tasks found")))
                .map(mapper::toResponse);
    }

}

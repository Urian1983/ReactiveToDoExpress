package service;

import dto.TaskRequest;
import dto.TaskResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskService {
    Mono<TaskResponse> createTask(TaskRequest newTask);
    Mono<TaskResponse> updateTask(Long id, TaskRequest updateTask);
    Mono<Void> deleteTask(Long id);
    Mono<TaskResponse> getTaskById(Long id);
    Flux<TaskResponse> getAllTasks();
}


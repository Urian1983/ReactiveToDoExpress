package urian1983.reactivetodoexpress.service;

import urian1983.reactivetodoexpress.dto.TaskRequest;
import urian1983.reactivetodoexpress.dto.TaskResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskService {
    Mono<TaskResponse> createTask(TaskRequest newTask);
    Mono<TaskResponse> updateTask(Long id, TaskRequest updateTask);
    Mono<Void> deleteTask(Long id);
    Mono<TaskResponse> getTaskById(Long id);
    Flux<TaskResponse> getAllTasks();
}


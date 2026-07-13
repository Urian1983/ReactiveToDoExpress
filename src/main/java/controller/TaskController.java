package controller;

import dto.TaskRequest;
import dto.TaskResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.TaskService;

@RequestMapping("/api")
@RestController
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }


    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest){
        return service.createTask(taskRequest);
    }

    @GetMapping("/tasks/{id}")
    public Mono<TaskResponse> getTasks(@PathVariable Long id)
    {
        return service.getTaskById(id);
    }

    @GetMapping("/tasks")
    public Flux<TaskResponse> getAllTasks() {
        return service.getAllTasks();
    }

    @PutMapping("/tasks/{id}")
    public Mono<TaskResponse> updateTask(@Valid @RequestBody TaskRequest taskRequest, @PathVariable Long id){
        return service.updateTask(id, taskRequest);
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteTask(@PathVariable Long id){
        return service.deleteTask(id);
    }
}

package controller;

import dto.TaskRequest;
import dto.TaskResponse;
import exception.NotFoundException;
import jakarta.validation.Valid;
import model.TaskPriority;
import model.TaskStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import service.TaskService;
import webform.TaskFormDTO;

@Controller
@RequestMapping("/tasks")
public class TaskViewController {
    private final TaskService service;

    public TaskViewController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public Mono<String> list(Model model) {
        return service.getAllTasks()
                .collectList()
                .doOnNext(tasks -> model.addAttribute("tasks", tasks))
                .thenReturn("tasks/list");
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("taskForm", new TaskFormDTO());
        model.addAttribute("priorities", TaskPriority.values());
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("isEdit", false);
        return "tasks/form";
    }

    @GetMapping("/{id}/edit")
    public Mono<String> editForm(@PathVariable Long id, Model model) {
        model.addAttribute("priorities", TaskPriority.values());
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("isEdit", true);

        return service.getTaskById(id)
                .map(this::toForm)
                .doOnNext(form -> model.addAttribute("taskForm", form))
                .thenReturn("tasks/form")
                .onErrorReturn(NotFoundException.class, "redirect:/tasks");
    }

    private TaskFormDTO toForm(TaskResponse task) {
        var form = new TaskFormDTO();
        form.setId(task.id());
        form.setDescription(task.description());
        form.setPriority(task.priority());
        form.setStatus(task.status());
        return form;
    }


    @PostMapping
    public Mono<String> create(@Valid @ModelAttribute("taskForm") TaskFormDTO form,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("priorities", TaskPriority.values());
            model.addAttribute("statuses", TaskStatus.values());
            model.addAttribute("isEdit", false);
            return Mono.just("tasks/form");
        }

        var request = new TaskRequest(form.getDescription(), form.getPriority(), form.getStatus());
        return service.createTask(request).thenReturn("redirect:/tasks");
    }

    @PostMapping("/{id}/update")
    public Mono<String> update(@PathVariable Long id,
                                @Valid @ModelAttribute("taskForm") TaskFormDTO form,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("priorities", TaskPriority.values());
            model.addAttribute("statuses", TaskStatus.values());
            model.addAttribute("isEdit", true);
            return Mono.just("tasks/form");
        }

        var request = new TaskRequest(form.getDescription(), form.getPriority(), form.getStatus());
        return service.updateTask(id, request).thenReturn("redirect:/tasks");
    }
    @PostMapping("/{id}/delete")
    public Mono<String> delete(@PathVariable Long id) {
        return service.deleteTask(id).thenReturn("redirect:/tasks");
    }
}

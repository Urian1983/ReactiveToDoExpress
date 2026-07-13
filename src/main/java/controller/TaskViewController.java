package controller;

import dto.TaskRequest;
import dto.TaskResponse;
import jakarta.validation.Valid;
import model.TaskPriority;
import model.TaskStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public String list(Model model) {
        var tasks = service.getAllTasks().collectList().block();
        model.addAttribute("tasks", tasks);
        return "tasks/list";
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
    public String editForm(@PathVariable Long id, Model model) {
        TaskResponse taskResponse = service.getTaskById(id).block();

        if (taskResponse == null) {
            return "redirect:/tasks";
        }

        var form = new TaskFormDTO();
        form.setId(taskResponse.id());
        form.setDescription(taskResponse.description());
        form.setPriority(taskResponse.priority());
        form.setStatus(taskResponse.status());

        model.addAttribute("taskForm", form);
        model.addAttribute("priorities", TaskPriority.values());
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("isEdit", true);

        return "tasks/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("taskForm") TaskFormDTO form,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("priorities", TaskPriority.values());
            model.addAttribute("statuses", TaskStatus.values());
            model.addAttribute("isEdit", false);
            return "tasks/form";
        }

        var request = new TaskRequest(form.getDescription(), form.getPriority(), form.getStatus());
        service.createTask(request).block();

        return "redirect:/tasks";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("taskForm") TaskFormDTO form,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("priorities", TaskPriority.values());
            model.addAttribute("statuses", TaskStatus.values());
            model.addAttribute("isEdit", true);
            return "tasks/form";
        }

        var request = new TaskRequest(form.getDescription(), form.getPriority(), form.getStatus());
        service.updateTask(id, request).block();

        return "redirect:/tasks";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.deleteTask(id).block();
        return "redirect:/tasks";
    }
}
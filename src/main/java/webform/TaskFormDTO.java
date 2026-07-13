package webform;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import model.TaskPriority;
import model.TaskStatus;

@Hidden
public class TaskFormDTO {

    private Long id;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;

    @NotNull(message = "Selecciona una prioridad")
    private TaskPriority priority;

    @NotNull(message = "Selecciona un estado")
    private TaskStatus status;

    public TaskFormDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}


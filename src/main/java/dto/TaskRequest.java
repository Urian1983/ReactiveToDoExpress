package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import model.TaskPriority;
import model.TaskStatus;

public record TaskRequest(
        @NotBlank
        String description,
        @NotNull
        TaskPriority priority,
        @NotNull
        TaskStatus status)
 {
}

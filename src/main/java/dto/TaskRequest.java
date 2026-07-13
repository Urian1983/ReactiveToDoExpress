package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import model.TaskPriority;
import model.TaskStatus;

@Schema(description = "Datos necesarios para crear o actualizar una tarea")
public record TaskRequest(

        @Schema(description = "Descripción de la tarea", example = "Implementar autenticación")
        @NotBlank
        String description,

        @Schema(description = "Prioridad de la tarea", example = "HIGH")
        @NotNull
        TaskPriority priority,

        @Schema(description = "Estado actual de la tarea", example = "IN_PROGRESS")
        @NotNull
        TaskStatus status)
 {
}

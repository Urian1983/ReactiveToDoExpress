package webform;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import model.TaskPriority;
import model.TaskStatus;

@Hidden
public record TaskFormDTO(Long id,

                          @NotBlank(message = "La descripción no puede estar vacía")
                          String description,

                          @NotNull(message = "Selecciona una prioridad")
                          TaskPriority priority,

                          @NotNull(message = "Selecciona un estado")
                          TaskStatus status) {
}

package webform;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import model.LogLevel;

@Hidden
public record AuditFormDTO (@NotNull(message = "Selecciona un nivel")
                             LogLevel level,
                             @NotNull(message = "Indica el ID de la tarea asociada")
                             Long taskId,
                             @NotBlank(message = "El mensaje no puede estar vacío")
                             String message)
{ }

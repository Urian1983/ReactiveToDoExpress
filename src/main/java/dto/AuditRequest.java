package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import model.LogLevel;

@Schema(description = "Datos necesarios para crear un registro de auditoría")
public record AuditRequest (
        @Schema(description = "Nivel del registro de auditoría", example = "INFO")
        LogLevel level,
        @Schema(description = "Identificador de la tarea asociada", example = "1")
        Long taskId,
        @Schema(description = "Mensaje descriptivo del evento", example = "Tarea actualizada correctamente")
        String message) {
}

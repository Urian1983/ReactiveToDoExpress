package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import model.LogLevel;

import java.time.LocalDateTime;

@Schema(description = "Representación de un registro de auditoría devuelto por la API")
public record AuditResponse(
        @Schema(description = "Identificador único del registro de auditoría", example = "1")
        Long id,
        @Schema(description = "Nivel del registro de auditoría", example = "INFO")
        LogLevel level,
        @Schema(description = "Identificador de la tarea asociada", example = "1")
        Long taskId,
        @Schema(description = "Mensaje descriptivo del evento", example = "Tarea actualizada correctamente")
        String message,
        @Schema(description = "Fecha y hora de creación del registro", example = "2026-07-13T10:15:30")
        LocalDateTime createdAt) {
}

package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import model.TaskPriority;
import model.TaskStatus;

import java.time.LocalDateTime;

@Schema(description = "Representación de una tarea devuelta por la API")
public record TaskResponse (
        @Schema(description = "Identificador único de la tarea", example = "1")
        Long id,
        @Schema(description = "Descripción de la tarea")
        String description,
        @Schema(description = "Nivel de prioridad de la tarea", example = "LOW")
        TaskPriority priority,
        @Schema(description ="Estado actual de la tarea", example = "IN_PROGRESS")
        TaskStatus status,
        @Schema(description = "Fecha y hora de creación", example = "2026-07-13T10:15:30")
        LocalDateTime createdAt,
        @Schema(description = "Fecha y hora de la última actualización", example = "2026-07-13T12:00:00")
        LocalDateTime updatedAt) {
}

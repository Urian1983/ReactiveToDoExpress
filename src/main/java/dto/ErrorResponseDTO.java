package dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Cuerpo de respuesta estándar para errores de la API")
public record ErrorResponseDTO(

        @Schema(description = "Mensaje descriptivo del error", example = "Tarea no encontrada")
        String message
) {
}

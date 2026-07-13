package dto;

import model.LogLevel;

import java.time.LocalDateTime;

public record AuditResponse(Long id, LogLevel level,
                            Long taskId,
                            String message,
                            LocalDateTime createdAt) {
}

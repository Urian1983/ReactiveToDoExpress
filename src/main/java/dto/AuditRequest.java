package dto;

import model.LogLevel;

public record AuditRequest ( LogLevel level,
               Long taskId,
               String message) {
}

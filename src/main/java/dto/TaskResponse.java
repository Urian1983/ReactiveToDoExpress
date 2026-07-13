package dto;

import model.TaskPriority;
import model.TaskStatus;

import java.time.LocalDateTime;

public record TaskResponse (Long id,
                            String description,
                            TaskPriority priority,
                            TaskStatus status,
                            LocalDateTime createdAt,
                            LocalDateTime updatedAt) {
}

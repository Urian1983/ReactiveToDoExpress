package model;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table("audit")
@AllArgsConstructor
@NoArgsConstructor
public class Audit {
    @Id
    private Long id;
    private LogLevel level;
    private Long taskId;
    private String message;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Audit(Long taskId, String message) {
        this.taskId = taskId;
        this.message = message;
        this.level = LogLevel.INFO;
        this.createdAt = LocalDateTime.now();
    }

    public Audit(LogLevel level, Long taskId, String message) {
        this.level = level;
        this.taskId = taskId;
        this.message = message;

    }
}

package urian1983.reactivetodoexpress.exception;

import urian1983.reactivetodoexpress.dto.ErrorResponseDTO;
import urian1983.reactivetodoexpress.model.Audit;
import urian1983.reactivetodoexpress.model.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;
import urian1983.reactivetodoexpress.repository.AuditRepository;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final AuditRepository auditRepository;

    public GlobalExceptionHandler(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponseDTO>> handleException(Exception ex) {
        return auditRepository.save(new Audit(LogLevel.ERROR, 0L, ex.getMessage()))
                .thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ErrorResponseDTO(ex.getMessage())));
    }

    @ExceptionHandler(NotFoundException.class)
    public Mono<ResponseEntity<ErrorResponseDTO>> handleNotFoundException(NotFoundException ex) {
        return auditRepository.save(new Audit(LogLevel.ERROR, 0L, ex.getMessage()))
                .thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponseDTO(ex.getMessage())));
    }
}
package controller;

import dto.AuditRequest;
import dto.AuditResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.AuditService;

@RestController
@RequestMapping("/api")
public class AuditController {
    private final AuditService service;

    public AuditController(AuditService service) {
        this.service = service;
    }

    @PostMapping("/audits")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AuditResponse> createAudit(@Valid @RequestBody AuditRequest auditRequest) {
        return service.createAudit(auditRequest);
    }

    @GetMapping("/audits/{id}")
    public Mono<AuditResponse> getAuditsById(@PathVariable Long id) {
        return service.getAuditById(id);
    }

    @GetMapping("/audits")
    public Flux<AuditResponse> getAllAudits() {
        return service.getAllAudits();
    }

}

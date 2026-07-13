package service;

import dto.AuditRequest;
import dto.AuditResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuditService {
    Mono<AuditResponse> createAudit(AuditRequest newAudit);
    Mono<AuditResponse> getAuditById(Long id);
    Flux<AuditResponse> getAllAudits();

}

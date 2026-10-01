package urian1983.reactivetodoexpress.service;

import urian1983.reactivetodoexpress.dto.AuditRequest;
import urian1983.reactivetodoexpress.dto.AuditResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuditService {
    Mono<AuditResponse> createAudit(AuditRequest newAudit);
    Mono<AuditResponse> getAuditById(Long id);
    Flux<AuditResponse> getAllAudits();

}

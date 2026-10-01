package urian1983.reactivetodoexpress.service;

import urian1983.reactivetodoexpress.dto.AuditRequest;
import urian1983.reactivetodoexpress.dto.AuditResponse;
import urian1983.reactivetodoexpress.exception.NotFoundException;
import urian1983.reactivetodoexpress.mapper.AuditMapper;
import urian1983.reactivetodoexpress.model.Audit;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import urian1983.reactivetodoexpress.repository.AuditRepository;

@Service
public class AuditServiceImpl implements AuditService {
    private final AuditRepository auditRepository;
    private final AuditMapper auditMapper;

    public AuditServiceImpl(AuditRepository auditRepository, AuditMapper auditMapper) {
        this.auditRepository = auditRepository;
        this.auditMapper = auditMapper;
    }

    @Override
    public Mono<AuditResponse> createAudit(AuditRequest newAudit) {
        Audit newAuditEntity = auditMapper.toEntity(newAudit);
        return auditRepository.save(newAuditEntity)
                .map(auditMapper::toResponse);
    }

    @Override
    public Mono<AuditResponse> getAuditById(Long id) {
        return auditRepository.findById(id)
                .switchIfEmpty(Mono.error(() -> new NotFoundException("Audit not found")))
                .map(auditMapper::toResponse);
    }


    @Override
    public Flux<AuditResponse> getAllAudits() {
        return auditRepository.findAll()
                .map(auditMapper::toResponse);
    }
}

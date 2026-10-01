package urian1983.reactivetodoexpress.repository;

import urian1983.reactivetodoexpress.model.Audit;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface AuditRepository extends R2dbcRepository<Audit, Long> {
}

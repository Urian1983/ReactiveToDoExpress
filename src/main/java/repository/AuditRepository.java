package repository;

import model.Audit;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface AuditRepository extends R2dbcRepository<Audit, Long> {
}

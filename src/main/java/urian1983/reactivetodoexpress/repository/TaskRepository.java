package urian1983.reactivetodoexpress.repository;

import urian1983.reactivetodoexpress.model.Task;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface TaskRepository extends R2dbcRepository<Task, Long> {
}

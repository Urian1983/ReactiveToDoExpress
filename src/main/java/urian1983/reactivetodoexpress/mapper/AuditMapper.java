package urian1983.reactivetodoexpress.mapper;
import urian1983.reactivetodoexpress.dto.AuditRequest;
import urian1983.reactivetodoexpress.dto.AuditResponse;
import urian1983.reactivetodoexpress.model.Audit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuditMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Audit toEntity(AuditRequest request);
    AuditResponse toResponse (Audit audit);
}

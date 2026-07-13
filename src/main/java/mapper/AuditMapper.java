package mapper;
import dto.AuditRequest;
import dto.AuditResponse;
import model.Audit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuditMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Audit toEntity(AuditRequest request);
    AuditResponse toResponse (Audit audit);
}

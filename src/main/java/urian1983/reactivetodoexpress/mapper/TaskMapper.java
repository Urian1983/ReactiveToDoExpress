package urian1983.reactivetodoexpress.mapper;

import urian1983.reactivetodoexpress.dto.TaskRequest;
import urian1983.reactivetodoexpress.dto.TaskResponse;
import urian1983.reactivetodoexpress.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Task toEntity(TaskRequest taskRequest);
    TaskResponse toResponse(Task task);
}


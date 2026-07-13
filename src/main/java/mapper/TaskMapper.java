package mapper;

import dto.TaskRequest;
import dto.TaskResponse;
import model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Task toEntity(TaskRequest taskRequest);
    TaskResponse toResponse(Task task);
    List<TaskResponse> toResponseList(List<Task> tasks);
}


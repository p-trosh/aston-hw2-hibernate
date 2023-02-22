package ru.trosh.astontrainee.mapper;

import org.springframework.stereotype.Component;
import ru.trosh.astontrainee.domain.Department;
import ru.trosh.astontrainee.domain.Task;
import ru.trosh.astontrainee.model.department.DepartmentShortResponse;
import ru.trosh.astontrainee.model.task.TaskFullResponse;
import ru.trosh.astontrainee.model.task.TaskRequest;
import ru.trosh.astontrainee.model.task.TaskShortResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapperImpl implements TaskMapper{

    @Override
    public List<TaskShortResponse> map(List<Task> tasks) {
        return tasks.stream()
                .map(this::mapToShortResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TaskFullResponse map(Task task) {
        return TaskFullResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .department(new DepartmentShortResponse(
                        task.getDepartment().getId(),
                        task.getDepartment().getName()))
                .build();
    }

    @Override
    public Task map(TaskRequest request) {
        return Task.builder()
                .title(request.getTitle())
                .description(request.getTitle())
                .department(Department.builder()
                        .id(request.getDepartmentId())
                        .build())
                .build();
    }

    @Override
    public TaskShortResponse mapToShortResponse(Task task) {
        return new TaskShortResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDepartment().getName());
    }
}

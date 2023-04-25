package ru.trosh.astontrainee.mapper;

import ru.trosh.astontrainee.domain.Task;
import ru.trosh.astontrainee.model.task.TaskFullResponse;
import ru.trosh.astontrainee.model.task.TaskRequest;
import ru.trosh.astontrainee.model.task.TaskShortResponse;

import java.util.List;

public interface TaskMapper {

    List<TaskShortResponse> map(List<Task> tasks);

    TaskFullResponse map(Task task);

    Task map(TaskRequest request);

    TaskShortResponse mapToShortResponse(Task task);
}

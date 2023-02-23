package ru.trosh.astontrainee.dao;

import ru.trosh.astontrainee.domain.Task;

public interface TaskDAO extends CrudOperations<Task, Long> {

    void addTaskToWorker(Long taskId, Long workerId);

    void deleteTaskFromWorker(Long taskId, Long workerId);
}

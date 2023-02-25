package ru.trosh.astontrainee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trosh.astontrainee.dao.TaskDAO;
import ru.trosh.astontrainee.domain.Task;
import ru.trosh.astontrainee.mapper.TaskMapper;
import ru.trosh.astontrainee.model.task.TaskFullResponse;
import ru.trosh.astontrainee.model.task.TaskRequest;
import ru.trosh.astontrainee.model.task.TaskShortResponse;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<TaskShortResponse> selectAll() {
        return taskMapper.map(taskDAO.selectAll());
    }

    @Override
    public TaskFullResponse selectById(Long id) {
        return taskMapper.map(findOrThrow(id));
    }

    @Override
    public TaskFullResponse create(TaskRequest request) {
        Task task = taskDAO.create(taskMapper.map(request));
        return taskMapper.map(task);
    }

    @Override
    public TaskFullResponse update(Long id, TaskRequest request) {
        findOrThrow(id);
        Task task = taskMapper.map(request);
        task.setId(id);
        return taskMapper.map(taskDAO.update(task));
    }

    @Override
    public void delete(Long id) {
        taskDAO.delete(id);
    }

    @Override
    public void addTaskToWorker(Long taskId, Long workerId) {
        taskDAO.addTaskToWorker(taskId, workerId);
    }

    @Override
    public void deleteTaskFromWorker(Long taskId, Long workerId) {
        taskDAO.deleteTaskFromWorker(taskId, workerId);
    }


    private Task findOrThrow(Long id) {
        return Optional.ofNullable(taskDAO.selectById(id))
                .orElseThrow(() -> new IllegalStateException("Task not found"));
    }
}

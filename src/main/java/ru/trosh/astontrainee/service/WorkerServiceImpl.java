package ru.trosh.astontrainee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trosh.astontrainee.dao.WorkerDAO;
import ru.trosh.astontrainee.domain.Worker;
import ru.trosh.astontrainee.mapper.WorkerMapper;
import ru.trosh.astontrainee.model.task.TaskFullResponse;
import ru.trosh.astontrainee.model.worker.WorkerFullResponse;
import ru.trosh.astontrainee.model.worker.WorkerRequest;
import ru.trosh.astontrainee.model.worker.WorkerShortResponse;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkerServiceImpl implements WorkerService{

    @Autowired
    private WorkerDAO workerDAO;

    @Autowired
    private WorkerMapper workerMapper;

    @Override
    public List<WorkerShortResponse> selectAll() {
        return workerMapper.map(workerDAO.selectAll());
    }

    @Override
    public WorkerFullResponse selectById(Long id) {
        return workerMapper.map(findOrThrow(id));
    }

    @Override
    public WorkerFullResponse create(WorkerRequest request) {
        Worker worker = workerDAO.create(workerMapper.map(request));
        return workerMapper.map(worker);
    }

    @Override
    public WorkerFullResponse update(Long id, WorkerRequest request) {
        findOrThrow(id);
        Worker worker = workerMapper.map(request);
        worker.setId(id);
        return workerMapper.map(workerDAO.update(worker));
    }

    @Override
    public void delete(Long id) {
        workerDAO.delete(id);
    }

    @Override
    public List<WorkerShortResponse> getAvailableWorkersByTask(TaskFullResponse task) {
        return selectAll().stream()
                .filter(w -> Objects.equals(w.getDepartmentName(), task.getDepartment().getName()))
                .filter(w -> !(task.getWorkers().contains(w)))
                .collect(Collectors.toList());
    }

    private Worker findOrThrow(Long id) {
        return Optional.ofNullable(workerDAO.selectById(id))
                .orElseThrow(() -> new IllegalStateException("Worker not found"));
    }
}

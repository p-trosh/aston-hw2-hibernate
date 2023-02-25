package ru.trosh.astontrainee.mapper;

import ru.trosh.astontrainee.domain.Worker;
import ru.trosh.astontrainee.model.worker.WorkerFullResponse;
import ru.trosh.astontrainee.model.worker.WorkerRequest;
import ru.trosh.astontrainee.model.worker.WorkerShortResponse;

import java.util.List;

public interface WorkerMapper {

    List<WorkerShortResponse> map(List<Worker> workers);

    WorkerFullResponse map(Worker worker);

    Worker map(WorkerRequest request);

    WorkerShortResponse mapToShortResponse(Worker worker);

}

package ru.trosh.astontrainee.mapper;

import org.springframework.stereotype.Component;
import ru.trosh.astontrainee.domain.Department;
import ru.trosh.astontrainee.domain.Speciality;
import ru.trosh.astontrainee.domain.Worker;
import ru.trosh.astontrainee.model.department.DepartmentShortResponse;
import ru.trosh.astontrainee.model.speciality.SpecialityShortResponse;
import ru.trosh.astontrainee.model.task.TaskShortResponse;
import ru.trosh.astontrainee.model.worker.WorkerFullResponse;
import ru.trosh.astontrainee.model.worker.WorkerRequest;
import ru.trosh.astontrainee.model.worker.WorkerShortResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkerMapperImpl implements WorkerMapper{
    @Override
    public List<WorkerShortResponse> map(List<Worker> workers) {
        return workers.stream()
                .map(this::mapToShortResponse)
                .collect(Collectors.toList());
    }

    @Override
    public WorkerFullResponse map(Worker worker) {
        return WorkerFullResponse.builder()
                .id(worker.getId())
                .firstName(worker.getFirstName())
                .lastName(worker.getLastName())
                .speciality(new SpecialityShortResponse(
                        worker.getSpeciality().getId(),
                        worker.getSpeciality().getName())
                        )
                .department(new DepartmentShortResponse(
                        worker.getDepartment().getId(),
                        worker.getDepartment().getName()))
                .tasks(worker.getTasks().stream()
                        .map(task -> new TaskShortResponse(
                                task.getId(),
                                task.getTitle(),
                                "",
                                ""))
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Worker map(WorkerRequest request) {
        return Worker.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .department(Department.builder()
                        .id(request.getDepartmentId())
                        .build())
                .speciality(Speciality.builder()
                        .id(request.getSpecialityId())
                        .build())
                .build();
    }

    @Override
    public WorkerShortResponse mapToShortResponse(Worker worker) {
        return WorkerShortResponse.builder()
                .id(worker.getId())
                .firstName(worker.getFirstName())
                .lastName(worker.getLastName())
                .specialityName(worker.getSpeciality().getName())
                .departmentName(worker.getDepartment().getName()).build();
    }
}

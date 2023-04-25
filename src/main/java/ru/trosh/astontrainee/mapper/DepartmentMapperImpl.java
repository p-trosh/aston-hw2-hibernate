package ru.trosh.astontrainee.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.trosh.astontrainee.domain.Department;
import ru.trosh.astontrainee.model.department.DepartmentFullResponse;
import ru.trosh.astontrainee.model.department.DepartmentRequest;
import ru.trosh.astontrainee.model.department.DepartmentShortResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DepartmentMapperImpl implements DepartmentMapper{

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private WorkerMapper workerMapper;

    @Override
    public List<DepartmentShortResponse> map(List<Department> departments) {
        return  departments.stream()
                .map(this::mapToShortResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentFullResponse map(Department department) {
        return DepartmentFullResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .tasks(taskMapper.map(department.getTasks()))
                .workers(workerMapper.map(department.getWorkers()))
                .build();
    }

    @Override
    public Department map(DepartmentRequest request) {
        return Department.builder()
                .name(request.getName())
                .build();
    }

    @Override
    public DepartmentShortResponse mapToShortResponse(Department department) {
        return new DepartmentShortResponse(department.getId(), department.getName());
    }
}

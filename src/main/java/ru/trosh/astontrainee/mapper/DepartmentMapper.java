package ru.trosh.astontrainee.mapper;

import ru.trosh.astontrainee.domain.Department;
import ru.trosh.astontrainee.model.department.DepartmentFullResponse;
import ru.trosh.astontrainee.model.department.DepartmentRequest;
import ru.trosh.astontrainee.model.department.DepartmentShortResponse;

import java.util.List;

public interface DepartmentMapper {

    List<DepartmentShortResponse> map(List<Department> departments);

    DepartmentFullResponse map(Department department);

    Department map(DepartmentRequest request);

    DepartmentShortResponse mapToShortResponse(Department department);

}

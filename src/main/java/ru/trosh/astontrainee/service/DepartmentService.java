package ru.trosh.astontrainee.service;

import ru.trosh.astontrainee.model.department.DepartmentFullResponse;
import ru.trosh.astontrainee.model.department.DepartmentRequest;
import ru.trosh.astontrainee.model.department.DepartmentShortResponse;

import java.util.List;

public interface DepartmentService {

    List<DepartmentShortResponse> selectAll();

    DepartmentFullResponse selectById(Long id);

    Long create(DepartmentRequest request);

    DepartmentFullResponse update(Long id, DepartmentRequest request);

    void delete(Long id);
}

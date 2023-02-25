package ru.trosh.astontrainee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trosh.astontrainee.dao.DepartmentDAO;
import ru.trosh.astontrainee.domain.Department;
import ru.trosh.astontrainee.mapper.DepartmentMapper;
import ru.trosh.astontrainee.model.department.DepartmentFullResponse;
import ru.trosh.astontrainee.model.department.DepartmentRequest;
import ru.trosh.astontrainee.model.department.DepartmentShortResponse;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentShortResponse> selectAll() {
        return departmentMapper.map(departmentDAO.selectAll());
    }

    @Override
    public DepartmentFullResponse selectById(Long id) {
        return departmentMapper.map(findOrThrow(id));
    }

    @Override
    public DepartmentFullResponse create(DepartmentRequest request) {
        Department department = departmentDAO.create(departmentMapper.map(request));
        return departmentMapper.map(department);
    }

    @Override
    public DepartmentFullResponse update(Long id, DepartmentRequest request) {
        Department department = findOrThrow(id);
        department.setName(request.getName());
        return departmentMapper.map(departmentDAO.update(department));
    }

    @Override
    public void delete(Long id) {
        departmentDAO.delete(id);
    }

    private Department findOrThrow(Long id) {
        return Optional.ofNullable(departmentDAO.selectById(id))
                .orElseThrow(() -> new IllegalStateException("Department not found"));
    }
}

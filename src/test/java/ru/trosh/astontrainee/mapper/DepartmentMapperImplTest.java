package ru.trosh.astontrainee.mapper;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.trosh.astontrainee.domain.Department;
import ru.trosh.astontrainee.domain.Task;
import ru.trosh.astontrainee.domain.Worker;
import ru.trosh.astontrainee.model.department.DepartmentFullResponse;
import ru.trosh.astontrainee.model.department.DepartmentRequest;
import ru.trosh.astontrainee.model.department.DepartmentShortResponse;
import ru.trosh.astontrainee.model.task.TaskShortResponse;
import ru.trosh.astontrainee.model.worker.WorkerShortResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class DepartmentMapperImplTest {

    @Mock
    TaskMapperImpl taskMapper;

    @Mock
    WorkerMapperImpl workerMapper;

    @InjectMocks
    DepartmentMapperImpl departmentMapper;

    @Test
    void mappingListDepartment_shouldReturnListDepartmentShortResponse() {
        List<Department> departments = List.of(new Department(), new Department());
        List<DepartmentShortResponse> responses = departmentMapper.map(departments);
        assertNotNull(responses);
        assertEquals(2, responses.size());
    }

    @Test
    void mappingDepartment_shouldReturnDepartmentShortResponse() {
        Department department = Department.builder()
                .id(1L)
                .name("name")
                .build();

        DepartmentShortResponse response = departmentMapper.mapToShortResponse(department);
        assertNotNull(response);
        assertEquals(1, response.getId());
        assertEquals("name", response.getName());
    }

    @Test
    void mappingDepartmentRequest_shouldReturnDepartment() {
        DepartmentRequest request = new DepartmentRequest();
        request.setName("name");

        Department department = departmentMapper.map(request);
        assertNotNull(department);
        assertNull(department.getId());
        assertEquals("name", department.getName());

    }

    @Test
    void mappingDepartment_shouldReturnDepartmentFullResponse() {
        List<Worker> workers = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        Department department = Department.builder()
                .id(1L)
                .name("name")
                .tasks(tasks)
                .workers(workers)
                .build();

        List<WorkerShortResponse> workerShortResponses = new ArrayList<>();
        List<TaskShortResponse> taskShortResponses = new ArrayList<>();
        Mockito.when(taskMapper.map(tasks)).thenReturn(taskShortResponses);
        Mockito.when(workerMapper.map(workers)).thenReturn(workerShortResponses);

        DepartmentFullResponse response = departmentMapper.map(department);
        assertEquals(1, response.getId());
        assertEquals("name", response.getName());
        assertEquals(taskShortResponses, response.getTasks());
        assertEquals(workerShortResponses, response.getWorkers());
    }
}
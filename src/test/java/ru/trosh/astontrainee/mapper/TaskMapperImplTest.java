package ru.trosh.astontrainee.mapper;

import org.junit.jupiter.api.Test;
import ru.trosh.astontrainee.domain.Department;
import ru.trosh.astontrainee.domain.Task;
import ru.trosh.astontrainee.domain.Worker;
import ru.trosh.astontrainee.model.task.TaskFullResponse;
import ru.trosh.astontrainee.model.task.TaskRequest;
import ru.trosh.astontrainee.model.task.TaskShortResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


class TaskMapperImplTest {

    TaskMapper taskMapper = new TaskMapperImpl();

    @Test
    void mappingTask_shouldReturnTaskShortResponse() {
        Task task = Task.builder()
                .id(1L)
                .title("title")
                .description("desc")
                .department(Department.builder()
                        .name("departmentName")
                        .build())
                .build();
        TaskShortResponse response = taskMapper.mapToShortResponse(task);
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("title", response.getTitle());
        assertEquals("desc", response.getDescription());
        assertEquals("departmentName", response.getDepartmentName());
    }

    @Test
    void mappingListOfTasks_shouldReturnListOfTaskShortResponse() {
        Task task1 = Task.builder()
                .department(Department.builder()
                        .name("name1").build()).build();
        Task task2 = Task.builder()
                .department(Department.builder()
                        .name("name2").build()).build();
        List<Task> tasks = List.of(task1, task2);
        List<TaskShortResponse> responses = taskMapper.map(tasks);
        assertNotNull(responses);
        assertEquals(2, responses.size());
    }

    @Test
    void mappingTaskRequest_shouldReturnTask() {
        TaskRequest request = new TaskRequest();
        request.setTitle("title");
        request.setDescription("desc");
        request.setDepartmentId(1L);

        Task task = taskMapper.map(request);
        assertNotNull(task);
        assertNull(task.getId());
        assertEquals("title", task.getTitle());
        assertEquals("desc", task.getDescription());
        assertEquals(1L, task.getDepartment().getId());
    }

    @Test
    void mappingTask_shouldReturnTaskFullResponse() {
        List<Worker> workers = List.of(
                Worker.builder()
                        .id(2L)
                        .firstName("firstName1")
                        .lastName("lastName1").build(),
                Worker.builder()
                        .id(3L)
                        .firstName("firstName2")
                        .lastName("lastName2").build());
        Task task = Task.builder()
                .id(1L)
                .title("title")
                .description("desc")
                .department(Department.builder()
                        .id(6L)
                        .name("departmentName")
                        .build())
                .workers(workers)
                .build();

        TaskFullResponse response = taskMapper.map(task);
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("title", response.getTitle());
        assertEquals("desc", response.getDescription());
        assertEquals(6L, response.getDepartment().getId());
        assertEquals("departmentName", response.getDepartment().getName());
        assertEquals(2L, response.getWorkers().get(0).getId());
        assertEquals(3L, response.getWorkers().get(1).getId());
        assertEquals("firstName1", response.getWorkers().get(0).getFirstName());
        assertEquals("firstName2", response.getWorkers().get(1).getFirstName());
        assertEquals("lastName1", response.getWorkers().get(0).getLastName());
        assertEquals("lastName2", response.getWorkers().get(1).getLastName());
    }
}
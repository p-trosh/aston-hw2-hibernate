package ru.trosh.astontrainee.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.trosh.astontrainee.domain.Department;
import ru.trosh.astontrainee.domain.Speciality;
import ru.trosh.astontrainee.domain.Worker;
import ru.trosh.astontrainee.model.worker.WorkerFullResponse;
import ru.trosh.astontrainee.model.worker.WorkerRequest;
import ru.trosh.astontrainee.model.worker.WorkerShortResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkerMapperImplTest {

    WorkerMapper workerMapper = new WorkerMapperImpl();
    Worker worker1;
    Worker worker2;

    @BeforeEach
    void init() {
        worker1 = Worker.builder()
                .id(1L)
                .firstName("firstName1")
                .lastName("lastName1")
                .speciality(Speciality.builder().id(3L).name("specialityName").build())
                .department(Department.builder().id(4L).name("departmentName").build())
                .tasks(new ArrayList<>()).build();

        worker2 = Worker.builder()
                .id(2L)
                .firstName("firstName2")
                .lastName("lastName2")
                .speciality(Speciality.builder().id(3L).name("specialityName").build())
                .department(Department.builder().id(4L).name("departmentName").build())
                .tasks(new ArrayList<>()).build();
    }

    @Test
    void mappingWorker_shouldReturnWorkerShortResponse() {
        WorkerShortResponse response = workerMapper.mapToShortResponse(worker1);
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("firstName1", response.getFirstName());
        assertEquals("lastName1", response.getLastName());
        assertEquals("specialityName", response.getSpecialityName());
        assertEquals("departmentName", response.getDepartmentName());
    }

    @Test
    void mappingListOfWorkers_shouldReturnListOfWorkerShortResponses() {
        List<Worker> workers = List.of(worker1, worker2);
        List<WorkerShortResponse> responses = workerMapper.map(workers);
        assertNotNull(responses);
        assertEquals(2, responses.size());
    }

    @Test
    void mappingWorkerRequest_shouldReturnWorker() {
        WorkerRequest request = new WorkerRequest();
        request.setFirstName("firstName");
        request.setLastName("lastName");
        request.setDepartmentId(1L);
        request.setSpecialityId(2L);

        Worker worker = workerMapper.map(request);
        assertNotNull(worker);
        assertNull(worker.getId());
        assertEquals("firstName", worker.getFirstName());
        assertEquals("lastName", worker.getLastName());
        assertEquals(1L, worker.getDepartment().getId());
        assertEquals(2L, worker.getSpeciality().getId());
    }

    @Test
    void mappingWorker_shouldReturnWorkerFullResponse() {
        WorkerFullResponse response = workerMapper.map(worker1);
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("firstName1", response.getFirstName());
        assertEquals("lastName1", response.getLastName());
        assertEquals("departmentName", response.getDepartment().getName());
        assertEquals("specialityName", response.getSpeciality().getName());
    }
}
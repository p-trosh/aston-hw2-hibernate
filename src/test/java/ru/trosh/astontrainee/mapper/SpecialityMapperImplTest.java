package ru.trosh.astontrainee.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.trosh.astontrainee.domain.Department;
import ru.trosh.astontrainee.domain.Speciality;
import ru.trosh.astontrainee.domain.Worker;
import ru.trosh.astontrainee.model.department.DepartmentShortResponse;
import ru.trosh.astontrainee.model.speciality.SpecialityFullResponse;
import ru.trosh.astontrainee.model.speciality.SpecialityRequest;
import ru.trosh.astontrainee.model.speciality.SpecialityShortResponse;
import ru.trosh.astontrainee.model.worker.WorkerShortResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SpecialityMapperImplTest {

    @InjectMocks
    SpecialityMapperImpl specialityMapper;

    @Mock
    WorkerMapperImpl workerMapper;

    @Test
    void mappingSpeciality_shouldReturnSpecialityShortResponse() {
        Speciality speciality = Speciality.builder()
                .id(1L)
                .name("name")
                .build();
        SpecialityShortResponse response = specialityMapper.mapToShortResponse(speciality);
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("name", response.getName());
    }

    @Test
    void mappingListOfSpecialities_shouldReturnListOfSpecialityShortResponses() {
        List<Speciality> specialities = List.of(new Speciality(), new Speciality());
        List<SpecialityShortResponse> responses = specialityMapper.map(specialities);
        assertNotNull(responses);
        assertEquals(2, responses.size());
    }

    @Test
    void mappingSpecialityRequest_shouldReturnSpeciality() {
        SpecialityRequest request = new SpecialityRequest();
        request.setName("name");

        Speciality speciality = specialityMapper.map(request);
        assertNotNull(speciality);
        assertNull(speciality.getId());
        assertEquals("name", speciality.getName());
    }

    @Test
    void mappingSpeciality_shouldReturnSpecialityFullResponse() {
        List<Worker> workers = new ArrayList<>();
        Speciality speciality = Speciality.builder()
                .id(1L)
                .name("name")
                .workers(workers)
                .build();

        List<WorkerShortResponse> workerShortResponses = new ArrayList<>();
        Mockito.when(workerMapper.map(workers)).thenReturn(workerShortResponses);
        SpecialityFullResponse response = specialityMapper.map(speciality);
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("name", response.getName());
        assertEquals(workerShortResponses, response.getWorkers());
    }
}
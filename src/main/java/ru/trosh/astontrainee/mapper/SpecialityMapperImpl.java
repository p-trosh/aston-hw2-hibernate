package ru.trosh.astontrainee.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.trosh.astontrainee.domain.Speciality;
import ru.trosh.astontrainee.model.speciality.SpecialityFullResponse;
import ru.trosh.astontrainee.model.speciality.SpecialityRequest;
import ru.trosh.astontrainee.model.speciality.SpecialityShortResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpecialityMapperImpl implements SpecialityMapper{

    @Autowired
    private WorkerMapper workerMapper;

    @Override
    public List<SpecialityShortResponse> map(List<Speciality> specialities) {
        return specialities.stream()
                .map(this::mapToShortResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SpecialityFullResponse map(Speciality speciality) {
        return SpecialityFullResponse.builder()
                .id(speciality.getId())
                .name(speciality.getName())
                .workers(workerMapper.map(speciality.getWorkers()))
                .build();
    }

    @Override
    public Speciality map(SpecialityRequest request) {
        return Speciality.builder()
                .name(request.getName())
                .build();
    }

    @Override
    public SpecialityShortResponse mapToShortResponse(Speciality speciality) {
        return new SpecialityShortResponse(speciality.getId(), speciality.getName());
    }
}

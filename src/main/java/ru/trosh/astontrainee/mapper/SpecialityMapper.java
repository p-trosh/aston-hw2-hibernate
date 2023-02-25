package ru.trosh.astontrainee.mapper;

import ru.trosh.astontrainee.domain.Speciality;
import ru.trosh.astontrainee.model.speciality.SpecialityFullResponse;
import ru.trosh.astontrainee.model.speciality.SpecialityRequest;
import ru.trosh.astontrainee.model.speciality.SpecialityShortResponse;

import java.util.List;

public interface SpecialityMapper {

    List<SpecialityShortResponse> map(List<Speciality> specialities);

    SpecialityFullResponse map(Speciality speciality);

    Speciality map(SpecialityRequest request);

    SpecialityShortResponse mapToShortResponse(Speciality speciality);
}

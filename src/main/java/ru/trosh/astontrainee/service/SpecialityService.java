package ru.trosh.astontrainee.service;

import ru.trosh.astontrainee.model.speciality.SpecialityFullResponse;
import ru.trosh.astontrainee.model.speciality.SpecialityRequest;
import ru.trosh.astontrainee.model.speciality.SpecialityShortResponse;

import java.util.List;

public interface SpecialityService {

    List<SpecialityShortResponse> selectAll();

    SpecialityFullResponse selectById(Long id);

    Long create(SpecialityRequest request);

    SpecialityFullResponse update(Long id, SpecialityRequest request);

    void delete(Long id);
}

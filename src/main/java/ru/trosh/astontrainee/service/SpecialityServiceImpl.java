package ru.trosh.astontrainee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trosh.astontrainee.dao.SpecialityDAO;
import ru.trosh.astontrainee.domain.Speciality;
import ru.trosh.astontrainee.mapper.SpecialityMapper;
import ru.trosh.astontrainee.model.speciality.SpecialityFullResponse;
import ru.trosh.astontrainee.model.speciality.SpecialityRequest;
import ru.trosh.astontrainee.model.speciality.SpecialityShortResponse;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialityServiceImpl implements SpecialityService{

    @Autowired
    private SpecialityDAO specialityDAO;

    @Autowired
    private SpecialityMapper specialityMapper;

    @Override
    public List<SpecialityShortResponse> selectAll() {
        return specialityMapper.map(specialityDAO.selectAll());
    }

    @Override
    public SpecialityFullResponse selectById(Long id) {
        return specialityMapper.map(findOrThrow(id));
    }

    @Override
    public SpecialityFullResponse create(SpecialityRequest request) {
        Speciality speciality = specialityDAO.create(specialityMapper.map(request));
        return specialityMapper.map(speciality);
    }

    @Override
    public SpecialityFullResponse update(Long id, SpecialityRequest request) {
        Speciality speciality = findOrThrow(id);
        speciality.setName(request.getName());
        return specialityMapper.map(specialityDAO.update(speciality));
    }

    @Override
    public void delete(Long id) {
        specialityDAO.delete(id);
    }

    private Speciality findOrThrow(Long id) {
        return Optional.ofNullable(specialityDAO.selectById(id))
                .orElseThrow(() -> new IllegalStateException("Speciality not found"));
    }
}

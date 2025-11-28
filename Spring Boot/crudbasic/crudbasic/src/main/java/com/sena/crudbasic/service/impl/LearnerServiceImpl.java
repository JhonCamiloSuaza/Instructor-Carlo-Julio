package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.model.Learner;
import com.sena.crudbasic.dto.LearnerDto;
import com.sena.crudbasic.repository.ILearnerRepository;
import com.sena.crudbasic.service.ILearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LearnerServiceImpl implements ILearnerService {

    @Autowired
    private ILearnerRepository repository;

    @Override
    public List<LearnerDto> getAll() {
        return repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public LearnerDto getById(int id) {
        return repository.findById(id).map(this::convertToDto).orElse(null);
    }

    @Override
    public LearnerDto save(LearnerDto dto) {
        Learner learner = new Learner();
        learner.setLearnerName(dto.getFullName());
        // Aquí podrías setear systemUser si lo deseas
        return convertToDto(repository.save(learner));
    }

    @Override
    public LearnerDto update(int id, LearnerDto dto) {
        return repository.findById(id).map(existing -> {
            existing.setLearnerName(dto.getFullName());
            return convertToDto(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    private LearnerDto convertToDto(Learner learner) {
        LearnerDto dto = new LearnerDto();
        dto.setId(learner.getId());
        dto.setFullName(learner.getLearnerName());
        if (learner.getSystemUser() != null) {
            dto.setSystemUserId(learner.getSystemUser().getId());
        }
        return dto;
    }
}

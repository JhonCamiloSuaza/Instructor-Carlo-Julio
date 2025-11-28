package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.model.TrainingModule;
import com.sena.crudbasic.dto.TrainingModuleDto;
import com.sena.crudbasic.repository.ITrainingModuleRepository;
import com.sena.crudbasic.service.ITrainingModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingModuleServiceImpl implements ITrainingModuleService {

    @Autowired
    private ITrainingModuleRepository repository;

    @Override
    public List<TrainingModuleDto> getAll() {
        return repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public TrainingModuleDto getById(int id) {
        return repository.findById(id).map(this::convertToDto).orElse(null);
    }

    @Override
    public TrainingModuleDto save(TrainingModuleDto dto) {
        TrainingModule tm = new TrainingModule();
        tm.setModuleTitle(dto.getModuleTitle());
        tm.setModuleCost(dto.getModuleCost());
        return convertToDto(repository.save(tm));
    }

    @Override
    public TrainingModuleDto update(int id, TrainingModuleDto dto) {
        return repository.findById(id).map(existing -> {
            existing.setModuleTitle(dto.getModuleTitle());
            existing.setModuleCost(dto.getModuleCost());
            return convertToDto(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    private TrainingModuleDto convertToDto(TrainingModule tm) {
        TrainingModuleDto dto = new TrainingModuleDto();
        dto.setId(tm.getId());
        dto.setModuleTitle(tm.getModuleTitle());
        dto.setModuleCost(tm.getModuleCost());
        return dto;
    }
}

package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.model.Registration;
import com.sena.crudbasic.dto.RegistrationDto;
import com.sena.crudbasic.repository.IRegistrationRepository;
import com.sena.crudbasic.repository.ILearnerRepository;
import com.sena.crudbasic.repository.ITrainingModuleRepository;
import com.sena.crudbasic.service.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements IRegistrationService {


    @Autowired
    private IRegistrationRepository repository;

    @Autowired
    private ILearnerRepository learnerRepository;

    @Autowired
    private ITrainingModuleRepository moduleRepository;

    @Override
    public List<RegistrationDto> getAll() {
        return repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public RegistrationDto getById(int id) {
        return repository.findById(id).map(this::convertToDto).orElse(null);
    }

    @Override
    public RegistrationDto save(RegistrationDto dto) {
        Registration reg = new Registration();
        reg.setRegisteredOn(dto.getRegisteredOn());
        reg.setLearner(learnerRepository.findById(dto.getLearnerId()).orElse(null));
        reg.setTrainingModule(moduleRepository.findById(dto.getTrainingModuleId()).orElse(null));
        return convertToDto(repository.save(reg));
    }

    @Override
    public RegistrationDto update(int id, RegistrationDto dto) {
        return repository.findById(id).map(existing -> {
            existing.setRegisteredOn(dto.getRegisteredOn());
            existing.setLearner(learnerRepository.findById(dto.getLearnerId()).orElse(null));
            existing.setTrainingModule(moduleRepository.findById(dto.getTrainingModuleId()).orElse(null));
            return convertToDto(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    private RegistrationDto convertToDto(Registration registration) {
        RegistrationDto dto = new RegistrationDto();
        dto.setId(registration.getId());
        dto.setRegisteredOn(registration.getRegisteredOn());
        dto.setLearnerId(registration.getLearner() != null ? registration.getLearner().getId() : 0);
        dto.setTrainingModuleId(registration.getTrainingModule() != null ? registration.getTrainingModule().getId() : 0);
        return dto;
    }
}

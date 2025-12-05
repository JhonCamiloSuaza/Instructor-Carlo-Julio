package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.dto.request.RegistrationCreateDTO;
import com.sena.crudbasic.dto.response.RegistrationResponseDTO;
import com.sena.crudbasic.model.Learner;
import com.sena.crudbasic.model.Registration;
import com.sena.crudbasic.model.TrainingModule;
import com.sena.crudbasic.repository.IRegistrationRepository;
import com.sena.crudbasic.repository.ILearnerRepository;
import com.sena.crudbasic.repository.ITrainingModuleRepository;
import com.sena.crudbasic.service.IRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegistrationServiceImpl
        extends GenericServiceImpl<Registration, RegistrationCreateDTO, RegistrationResponseDTO, Integer>
        implements IRegistrationService {

    private final IRegistrationRepository registrationRepository;
    private final ILearnerRepository learnerRepository;
    private final ITrainingModuleRepository trainingModuleRepository;

    @Override
    public List<RegistrationResponseDTO> findByLearnerId(Integer learnerId) {
        return registrationRepository.findByLearner_Id(learnerId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RegistrationResponseDTO> findByTrainingModuleId(Integer trainingModuleId) {
        return registrationRepository.findByTrainingModule_Id(trainingModuleId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    protected Registration mapToEntity(RegistrationCreateDTO dto) {
        Learner learner = learnerRepository.findById(dto.getLearnerId())
                .orElseThrow(() -> new RuntimeException("Aprendiz no encontrado con ID: " + dto.getLearnerId()));

        TrainingModule trainingModule = trainingModuleRepository.findById(dto.getTrainingModuleId())
                .orElseThrow(() -> new RuntimeException("Módulo de formación no encontrado con ID: " + dto.getTrainingModuleId()));

        return Registration.builder()
                .learner(learner)
                .trainingModule(trainingModule)
                .registeredOn(dto.getRegisteredOn())
                .build();
    }

    @Override
    protected void updateEntity(Registration entity, RegistrationCreateDTO dto) {
        Learner learner = learnerRepository.findById(dto.getLearnerId())
                .orElseThrow(() -> new RuntimeException("Aprendiz no encontrado con ID: " + dto.getLearnerId()));

        TrainingModule trainingModule = trainingModuleRepository.findById(dto.getTrainingModuleId())
                .orElseThrow(() -> new RuntimeException("Módulo de formación no encontrado con ID: " + dto.getTrainingModuleId()));

        entity.setLearner(learner);
        entity.setTrainingModule(trainingModule);
        entity.setRegisteredOn(dto.getRegisteredOn());
    }

    @Override
    protected RegistrationResponseDTO mapToResponse(Registration registration) {
        return RegistrationResponseDTO.builder()
                .id(registration.getId())
                .learnerId(registration.getLearner().getId())
                .trainingModuleId(registration.getTrainingModule().getId())
                .registeredOn(registration.getRegisteredOn())
                .createdAt(registration.getCreatedAt())
                .updatedAt(registration.getUpdatedAt())
                .build();
    }

    @Override
    protected JpaRepository<Registration, Integer> getRepository() {
        return registrationRepository;
    }

    @Override
    protected String getEntityName() {
        return "Registro";
    }
}

package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.dto.request.TrainingModuleCreateDTO;
import com.sena.crudbasic.dto.response.TrainingModuleResponseDTO;
import com.sena.crudbasic.model.TrainingModule;
import com.sena.crudbasic.repository.ITrainingModuleRepository;
import com.sena.crudbasic.service.ITrainingModuleService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TrainingModuleServiceImpl
        extends GenericServiceImpl<TrainingModule, TrainingModuleCreateDTO, TrainingModuleResponseDTO, Integer>
        implements ITrainingModuleService {

    private final ITrainingModuleRepository trainingModuleRepository;

    @Override
    public TrainingModuleResponseDTO findByModuleTitle(String moduleTitle) {
        TrainingModule module = trainingModuleRepository.findByModuleTitle(moduleTitle)
                .orElseThrow(() -> new RuntimeException(
                        "Módulo no encontrado con el título: " + moduleTitle));
        return mapToResponse(module);
    }

    @Override
    public List<TrainingModuleResponseDTO> findByCostLessThanEqual(BigDecimal maxCost) {
        return trainingModuleRepository.findByModuleCostLessThanEqual(maxCost)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    protected TrainingModule mapToEntity(TrainingModuleCreateDTO dto) {
        return TrainingModule.builder()
                .moduleTitle(dto.getModuleTitle())
                .moduleCost(dto.getModuleCost())
                .build();
    }

    @Override
    protected void updateEntity(TrainingModule entity, TrainingModuleCreateDTO dto) {
        entity.setModuleTitle(dto.getModuleTitle());
        entity.setModuleCost(dto.getModuleCost());
    }

    @Override
    protected TrainingModuleResponseDTO mapToResponse(TrainingModule module) {
        return TrainingModuleResponseDTO.builder()
                .id(module.getId())
                .moduleTitle(module.getModuleTitle())
                .moduleCost(module.getModuleCost())
                .createdAt(module.getCreatedAt())
                .updatedAt(module.getUpdatedAt())
                .build();
    }

    @Override
    protected JpaRepository<TrainingModule, Integer> getRepository() {
        return trainingModuleRepository;
    }

    @Override
    protected String getEntityName() {
        return "Módulo de Formación";
    }
}

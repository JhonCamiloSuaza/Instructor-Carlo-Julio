package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.dto.request.ModuleAssignmentCreateDTO;
import com.sena.crudbasic.dto.response.ModuleAssignmentResponseDTO;
import com.sena.crudbasic.model.ModuleAssignment;
import com.sena.crudbasic.model.ProgramType;
import com.sena.crudbasic.model.TrainingModule;
import com.sena.crudbasic.repository.IModuleAssignmentRepository;
import com.sena.crudbasic.repository.IProgramTypeRepository;
import com.sena.crudbasic.repository.ITrainingModuleRepository;
import com.sena.crudbasic.service.ModuleAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ModuleAssignmentServiceImpl
        extends GenericServiceImpl<ModuleAssignment, ModuleAssignmentCreateDTO, ModuleAssignmentResponseDTO, Integer>
        implements ModuleAssignmentService {

    private final IModuleAssignmentRepository moduleAssignmentRepository;
    private final IProgramTypeRepository programTypeRepository;
    private final ITrainingModuleRepository trainingModuleRepository;

    // Verifica si ya existe una asignación entre tipo de programa y módulo
    @Override
    public boolean existsByProgramTypeAndModule(Integer programTypeId, Integer trainingModuleId) {
        return moduleAssignmentRepository
                .findByProgramType_Id(programTypeId)
                .stream()
                .anyMatch(ma -> ma.getTrainingModule().getId().equals(trainingModuleId));
    }

    // Crear asignación
    @Override
    @Transactional
    public ModuleAssignmentResponseDTO create(ModuleAssignmentCreateDTO dto) {
        ModuleAssignment entity = mapToEntity(dto);
        moduleAssignmentRepository.save(entity);
        return mapToResponse(entity);
    }

    // Actualizar asignación
    @Override
    @Transactional
    public ModuleAssignmentResponseDTO update(Integer id, ModuleAssignmentCreateDTO dto) {
        ModuleAssignment existing = moduleAssignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada con ID: " + id));
        updateEntity(existing, dto);
        existing = moduleAssignmentRepository.save(existing);
        return mapToResponse(existing);
    }

    // Eliminar asignación
    @Override
    @Transactional
    public void delete(Integer id) {
        moduleAssignmentRepository.deleteById(id);
    }

    // Obtener asignaciones por tipo de programa
    @Override
    @Transactional(readOnly = true)
    public List<ModuleAssignmentResponseDTO> findByProgramTypeId(Integer programTypeId) {
        return moduleAssignmentRepository.findByProgramType_Id(programTypeId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Obtener asignaciones por módulo de entrenamiento
    @Override
    @Transactional(readOnly = true)
    public List<ModuleAssignmentResponseDTO> findByTrainingModuleId(Integer trainingModuleId) {
        return moduleAssignmentRepository.findByTrainingModule_Id(trainingModuleId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Mapea DTO a entidad
    @Override
    protected ModuleAssignment mapToEntity(ModuleAssignmentCreateDTO dto) {
        ProgramType programType = programTypeRepository.findById(dto.getProgramTypeId())
                .orElseThrow(() -> new RuntimeException("Tipo de programa no encontrado"));

        TrainingModule trainingModule = trainingModuleRepository.findById(dto.getTrainingModuleId())
                .orElseThrow(() -> new RuntimeException("Módulo de entrenamiento no encontrado"));

        if (existsByProgramTypeAndModule(dto.getProgramTypeId(), dto.getTrainingModuleId())) {
            throw new RuntimeException("Esta asignación ya existe.");
        }

        return ModuleAssignment.builder()
                .programType(programType)
                .trainingModule(trainingModule)
                .active(true)
                .build();
    }

    // Actualiza entidad con datos del DTO
    @Override
    protected void updateEntity(ModuleAssignment assignment, ModuleAssignmentCreateDTO dto) {
        ProgramType programType = programTypeRepository.findById(dto.getProgramTypeId())
                .orElseThrow(() -> new RuntimeException("Tipo de programa no encontrado"));

        TrainingModule trainingModule = trainingModuleRepository.findById(dto.getTrainingModuleId())
                .orElseThrow(() -> new RuntimeException("Módulo de entrenamiento no encontrado"));

        assignment.setProgramType(programType);
        assignment.setTrainingModule(trainingModule);
    }

    // Mapea entidad a DTO
    @Override
    protected ModuleAssignmentResponseDTO mapToResponse(ModuleAssignment assignment) {
        return ModuleAssignmentResponseDTO.builder()
                .id(assignment.getId())
                .programTypeId(assignment.getProgramType().getId())
                .trainingModuleId(assignment.getTrainingModule().getId())
                .createdAt(assignment.getCreatedAt())
                .updatedAt(assignment.getUpdatedAt())
                .build();
    }

    @Override
    protected JpaRepository<ModuleAssignment, Integer> getRepository() {
        return moduleAssignmentRepository;
    }

    @Override
    protected String getEntityName() {
        return "Asignación de Módulo";
    }
}

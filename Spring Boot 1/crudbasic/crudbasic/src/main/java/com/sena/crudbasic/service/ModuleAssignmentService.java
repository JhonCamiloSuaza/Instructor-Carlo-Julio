package com.sena.crudbasic.service;

import com.sena.crudbasic.dto.request.ModuleAssignmentCreateDTO;
import com.sena.crudbasic.dto.response.ModuleAssignmentResponseDTO;

import java.util.List;

public interface ModuleAssignmentService
        extends GenericService<ModuleAssignmentCreateDTO, ModuleAssignmentResponseDTO, Integer> {

    boolean existsByProgramTypeAndModule(Integer programTypeId, Integer trainingModuleId);

    // Agregar explícitamente create para que el controller lo vea
    ModuleAssignmentResponseDTO create(ModuleAssignmentCreateDTO dto);

    // Métodos de filtrado opcionales
    List<ModuleAssignmentResponseDTO> findByProgramTypeId(Integer programTypeId);
    List<ModuleAssignmentResponseDTO> findByTrainingModuleId(Integer trainingModuleId);
}

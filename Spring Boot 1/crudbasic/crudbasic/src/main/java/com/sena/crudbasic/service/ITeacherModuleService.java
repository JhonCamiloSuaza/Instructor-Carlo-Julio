package com.sena.crudbasic.service;

import com.sena.crudbasic.dto.request.TeacherModuleCreateDTO;
import com.sena.crudbasic.dto.response.TeacherModuleResponseDTO;

public interface ITeacherModuleService 
        extends GenericService<TeacherModuleCreateDTO, TeacherModuleResponseDTO, Integer> {

    // Verificar si ya existe una asignación de docente a módulo
    boolean existsByTeacherAndModule(Integer teacherId, Integer trainingModuleId);
}

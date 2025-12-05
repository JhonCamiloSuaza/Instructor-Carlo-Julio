package com.sena.crudbasic.service;

import com.sena.crudbasic.dto.request.TeacherCreateDTO;
import com.sena.crudbasic.dto.response.TeacherResponseDTO;

import java.util.List;

public interface ITeacherService extends GenericService<TeacherCreateDTO, TeacherResponseDTO, Integer> {

    // Buscar docente por nombre completo
    TeacherResponseDTO findByTeacherName(String teacherName);

    // Buscar docentes por especialidad
    List<TeacherResponseDTO> findByExpertise(String expertise);

    // Lista de docentes activos (opcional si tu entidad tiene campo activo)
    List<TeacherResponseDTO> findByActiveTrue();
}

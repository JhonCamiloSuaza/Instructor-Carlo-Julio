package com.sena.crudbasic.service;

import com.sena.crudbasic.dto.request.ProgramTypeCreateDTO;
import com.sena.crudbasic.dto.response.ProgramTypeResponseDTO;

import java.util.List;

public interface IProgramTypeService extends GenericService<ProgramTypeCreateDTO, ProgramTypeResponseDTO, Integer> {

    // Buscar tipo de programa por nombre
    ProgramTypeResponseDTO findByTypeName(String typeName);

    // Verificar si existe un tipo de programa con ese nombre
    boolean existsByTypeName(String typeName);

    // Lista de tipos de programa activos (opcional, si tu entidad tiene campo activo)
    List<ProgramTypeResponseDTO> findByActiveTrue();
}

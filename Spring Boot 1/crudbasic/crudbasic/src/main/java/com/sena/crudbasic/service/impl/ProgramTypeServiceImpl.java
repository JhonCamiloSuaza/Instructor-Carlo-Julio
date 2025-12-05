package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.dto.request.ProgramTypeCreateDTO;
import com.sena.crudbasic.dto.response.ProgramTypeResponseDTO;
import com.sena.crudbasic.model.ProgramType;
import com.sena.crudbasic.repository.IProgramTypeRepository;
import com.sena.crudbasic.service.IProgramTypeService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProgramTypeServiceImpl 
        extends GenericServiceImpl<ProgramType, ProgramTypeCreateDTO, ProgramTypeResponseDTO, Integer>
        implements IProgramTypeService {

    private final IProgramTypeRepository programTypeRepository;

    @Override
    public ProgramTypeResponseDTO findByTypeName(String typeName) {
        ProgramType programType = programTypeRepository.findByTypeName(typeName)
                .orElseThrow(() -> new RuntimeException(
                        "Tipo de programa no encontrado con el nombre: " + typeName));
        return mapToResponse(programType);
    }

    @Override
    public boolean existsByTypeName(String typeName) {
        return programTypeRepository.existsByTypeName(typeName);
    }

    @Override
    public List<ProgramTypeResponseDTO> findByActiveTrue() {
        // Como no tienes campo "active", retornamos todos los registros
        return programTypeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    protected ProgramType mapToEntity(ProgramTypeCreateDTO dto) {
        return ProgramType.builder()
                .typeName(dto.getTypeName())
                .build();
    }

    @Override
    protected void updateEntity(ProgramType entity, ProgramTypeCreateDTO dto) {
        entity.setTypeName(dto.getTypeName());
    }

    @Override
    protected ProgramTypeResponseDTO mapToResponse(ProgramType programType) {
        return ProgramTypeResponseDTO.builder()
                .id(programType.getId())
                .typeName(programType.getTypeName())
                .createdAt(programType.getCreatedAt())
                .updatedAt(programType.getUpdatedAt())
                .build();
    }

    @Override
    protected JpaRepository<ProgramType, Integer> getRepository() {
        return programTypeRepository;
    }

    @Override
    protected String getEntityName() {
        return "Tipo de Programa";
    }
}

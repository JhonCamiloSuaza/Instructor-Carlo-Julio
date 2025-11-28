package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.model.ProgramType;
import com.sena.crudbasic.dto.ProgramTypeDto;
import com.sena.crudbasic.repository.IProgramTypeRepository;
import com.sena.crudbasic.service.IProgramTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramTypeServiceImpl implements IProgramTypeService {

    @Autowired
    private IProgramTypeRepository repository;

    @Override
    public List<ProgramTypeDto> getAll() {
        return repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ProgramTypeDto getById(int id) {
        return repository.findById(id).map(this::convertToDto).orElse(null);
    }

    @Override
    public ProgramTypeDto save(ProgramTypeDto dto) {
        ProgramType programType = new ProgramType();
        programType.setTypeName(dto.getTypeName());
        return convertToDto(repository.save(programType));
    }

    @Override
    public ProgramTypeDto update(int id, ProgramTypeDto dto) {
        return repository.findById(id).map(existing -> {
            existing.setTypeName(dto.getTypeName());
            return convertToDto(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    private ProgramTypeDto convertToDto(ProgramType programType) {
        ProgramTypeDto dto = new ProgramTypeDto();
        dto.setId(programType.getId());
        dto.setTypeName(programType.getTypeName());
        return dto;
    }
}

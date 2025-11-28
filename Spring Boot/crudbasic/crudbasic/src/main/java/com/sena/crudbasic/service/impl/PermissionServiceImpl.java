package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.model.Permission;
import com.sena.crudbasic.dto.PermissionDto;
import com.sena.crudbasic.repository.IPermissionRepository;
import com.sena.crudbasic.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionRepository repository;

    @Override
    public List<PermissionDto> getAll() {
        return repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public PermissionDto getById(int id) {
        return repository.findById(id).map(this::convertToDto).orElse(null);
    }

    @Override
    public PermissionDto save(PermissionDto dto) {
        Permission permission = new Permission();
        permission.setPermissionName(dto.getPermissionName());
        return convertToDto(repository.save(permission));
    }

    @Override
    public PermissionDto update(int id, PermissionDto dto) {
        return repository.findById(id).map(existing -> {
            existing.setPermissionName(dto.getPermissionName());
            return convertToDto(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    private PermissionDto convertToDto(Permission permission) {
        PermissionDto dto = new PermissionDto();
        dto.setId(permission.getId());
        dto.setPermissionName(permission.getPermissionName());
        return dto;
    }
}

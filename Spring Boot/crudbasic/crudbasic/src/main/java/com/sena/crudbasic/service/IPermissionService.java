package com.sena.crudbasic.service;

import com.sena.crudbasic.dto.PermissionDto;

import java.util.List;

public interface IPermissionService {
    List<PermissionDto> getAll();
    PermissionDto getById(int id);
    PermissionDto save(PermissionDto dto);
    PermissionDto update(int id, PermissionDto dto);
    void delete(int id);
}

package com.sena.crudbasic.service;

import com.sena.crudbasic.dto.UserPermissionDto;
import java.util.List;

public interface IUserPermissionService {
    List<UserPermissionDto> getAll();
    UserPermissionDto getById(int id);
    UserPermissionDto save(UserPermissionDto dto);
    UserPermissionDto update(int id, UserPermissionDto dto);
    void delete(int id);
}

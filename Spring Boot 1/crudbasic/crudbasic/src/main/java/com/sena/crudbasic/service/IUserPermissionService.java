package com.sena.crudbasic.service;

import com.sena.crudbasic.dto.request.UserPermissionCreateDTO;
import com.sena.crudbasic.dto.response.UserPermissionResponseDTO;

import java.util.List;

public interface IUserPermissionService extends GenericService<UserPermissionCreateDTO, UserPermissionResponseDTO, Integer> {

    UserPermissionResponseDTO create(UserPermissionCreateDTO dto);

    List<UserPermissionResponseDTO> findByUserId(Integer userId);

    List<UserPermissionResponseDTO> findByPermissionId(Integer permissionId);
}

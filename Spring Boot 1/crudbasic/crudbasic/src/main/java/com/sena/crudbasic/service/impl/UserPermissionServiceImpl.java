package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.dto.request.UserPermissionCreateDTO;
import com.sena.crudbasic.dto.response.UserPermissionResponseDTO;
import com.sena.crudbasic.model.Permission;
import com.sena.crudbasic.model.UserPermission;
import com.sena.crudbasic.model.UserSystms;
import com.sena.crudbasic.repository.IPermissionRepository;
import com.sena.crudbasic.repository.IUserPermissionRepository;
import com.sena.crudbasic.repository.IUserSystmsRepository;
import com.sena.crudbasic.service.IUserPermissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserPermissionServiceImpl
        extends GenericServiceImpl<UserPermission, UserPermissionCreateDTO, UserPermissionResponseDTO, Integer>
        implements IUserPermissionService {

    private final IUserPermissionRepository userPermissionRepository;
    private final IUserSystmsRepository userSystmsRepository;
    private final IPermissionRepository permissionRepository;

    @Override
    @Transactional
    public UserPermissionResponseDTO create(UserPermissionCreateDTO dto) {
        UserSystms user = userSystmsRepository.findById(dto.getSystemUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getSystemUserId()));

        Permission permission = permissionRepository.findById(dto.getPermissionId())
                .orElseThrow(() -> new RuntimeException("Permiso no encontrado con ID: " + dto.getPermissionId()));

        UserPermission entity = UserPermission.builder()
                .systemUser(user)
                .permission(permission)
                .build();

        UserPermission saved = userPermissionRepository.save(entity);
        return mapToResponse(saved);
    }

    @Override
    public List<UserPermissionResponseDTO> findByUserId(Integer userId) {
        return userPermissionRepository.findBySystemUser_Id(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserPermissionResponseDTO> findByPermissionId(Integer permissionId) {
        return userPermissionRepository.findByPermission_Id(permissionId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    protected UserPermission mapToEntity(UserPermissionCreateDTO dto) {
        throw new UnsupportedOperationException("Usar create() para permisos.");
    }

    @Override
    protected void updateEntity(UserPermission entity, UserPermissionCreateDTO dto) {
        throw new UnsupportedOperationException("No implementado aún.");
    }

    @Override
    protected UserPermissionResponseDTO mapToResponse(UserPermission entity) {
        return UserPermissionResponseDTO.builder()
                .id(entity.getId())
                .systemUserId(entity.getSystemUser().getId())
                .permissionId(entity.getPermission().getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    protected JpaRepository<UserPermission, Integer> getRepository() {
        return userPermissionRepository;
    }

    @Override
    protected String getEntityName() {
        return "User Permission";
    }
}

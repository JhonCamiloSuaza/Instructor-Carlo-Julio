package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.dto.request.PermissionCreateDTO;
import com.sena.crudbasic.dto.response.PermissionResponseDTO;
import com.sena.crudbasic.model.Permission;
import com.sena.crudbasic.repository.IPermissionRepository;
import com.sena.crudbasic.service.IPermissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PermissionServiceImpl 
        extends GenericServiceImpl<Permission, PermissionCreateDTO, PermissionResponseDTO, Integer>
        implements IPermissionService {

    private final IPermissionRepository permissionRepository;

    @Override
    public PermissionResponseDTO findByPermissionName(String permissionName) {
        Permission permission = permissionRepository.findByPermissionName(permissionName)
                .orElseThrow(() -> new RuntimeException(
                        "Permiso no encontrado con el nombre: " + permissionName));
        return mapToResponse(permission);
    }

    @Override
    public boolean existsByPermissionName(String permissionName) {
        return permissionRepository.existsByPermissionName(permissionName);
    }

    @Override
    public List<PermissionResponseDTO> findByActiveTrue() {
        // Como no tienes campo "active", retornamos todos los permisos
        return permissionRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    protected Permission mapToEntity(PermissionCreateDTO dto) {
        return Permission.builder()
                .permissionName(dto.getPermissionName())
                .build();
    }

    @Override
    protected void updateEntity(Permission entity, PermissionCreateDTO dto) {
        entity.setPermissionName(dto.getPermissionName());
    }

    @Override
    protected PermissionResponseDTO mapToResponse(Permission permission) {
        return PermissionResponseDTO.builder()
                .id(permission.getId())
                .permissionName(permission.getPermissionName())
                .createdAt(permission.getCreatedAt())
                .updatedAt(permission.getUpdatedAt())
                .build();
    }

    @Override
    protected JpaRepository<Permission, Integer> getRepository() {
        return permissionRepository;
    }

    @Override
    protected String getEntityName() {
        return "Permiso";
    }
}

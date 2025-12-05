package com.sena.crudbasic.service;

import com.sena.crudbasic.dto.request.PermissionCreateDTO;
import com.sena.crudbasic.dto.response.PermissionResponseDTO;

import java.util.List;

public interface IPermissionService extends GenericService<PermissionCreateDTO, PermissionResponseDTO, Integer> {

    // Buscar permiso por nombre
    PermissionResponseDTO findByPermissionName(String permissionName);

    // Verificar si existe un permiso con ese nombre
    boolean existsByPermissionName(String permissionName);

    // Lista de permisos activos (opcional, si tu entidad tiene campo activo)
    List<PermissionResponseDTO> findByActiveTrue();
}

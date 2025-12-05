package com.sena.crudbasic.repository;

import com.sena.crudbasic.model.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para la entidad UserPermission.
 */
@Repository
public interface IUserPermissionRepository extends JpaRepository<UserPermission, Integer> {

    /**
     * Buscar todos los permisos asignados a un usuario por su ID.
     */
    List<UserPermission> findBySystemUser_Id(Integer systemUserId);

    /**
     * Buscar todos los usuarios que tienen un permiso específico por su ID.
     */
    List<UserPermission> findByPermission_Id(Integer permissionId);

    /**
     * Buscar todas las asignaciones activas.
     */
    List<UserPermission> findByActiveTrue();

    /**
     * Buscar una asignación por ID.
     */
    Optional<UserPermission> findById(Integer id);

    /**
     * Verificar si existe una asignación con un ID específico.
     */
    boolean existsById(Integer id);
}

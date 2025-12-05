package com.sena.crudbasic.repository;

import com.sena.crudbasic.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para la entidad Permission.
 */
@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Integer> {

    /**
     * Buscar un permiso por su nombre.
     */
    Optional<Permission> findByPermissionName(String permissionName);

    /**
     * Buscar todos los permisos activos.
     */
    List<Permission> findByActiveTrue();

    /**
     * Verificar si existe un permiso con un nombre específico.
     */
    boolean existsByPermissionName(String permissionName);
}

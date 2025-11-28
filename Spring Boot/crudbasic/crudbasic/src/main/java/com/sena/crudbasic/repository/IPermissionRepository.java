package com.sena.crudbasic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crudbasic.model.Permission;

public interface IPermissionRepository extends JpaRepository<Permission, Integer> {
}

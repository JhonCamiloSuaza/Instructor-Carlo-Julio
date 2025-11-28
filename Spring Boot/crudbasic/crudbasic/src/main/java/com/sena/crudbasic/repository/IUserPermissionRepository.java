package com.sena.crudbasic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crudbasic.model.User_Permission;

public interface IUserPermissionRepository extends JpaRepository<User_Permission, Integer> {
}

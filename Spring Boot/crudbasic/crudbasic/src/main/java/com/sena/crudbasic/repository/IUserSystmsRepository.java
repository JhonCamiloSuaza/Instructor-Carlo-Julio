package com.sena.crudbasic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crudbasic.model.UserSystms;

public interface IUserSystmsRepository extends JpaRepository<UserSystms, Integer> {
}

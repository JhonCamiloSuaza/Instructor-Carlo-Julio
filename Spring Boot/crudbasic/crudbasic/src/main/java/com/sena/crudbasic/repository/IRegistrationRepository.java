package com.sena.crudbasic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crudbasic.model.Registration;

public interface IRegistrationRepository extends JpaRepository<Registration, Integer> {
}

package com.sena.crudbasic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crudbasic.model.ProgramType;

public interface IProgramTypeRepository extends JpaRepository<ProgramType, Integer> {
}

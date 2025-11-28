package com.sena.crudbasic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crudbasic.model.Teacher_Module;

public interface ITeacherModuleRepository extends JpaRepository<Teacher_Module, Integer> {
}

package com.sena.crudbasic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crudbasic.model.Teacher;

public interface ITeacherRepository extends JpaRepository<Teacher, Integer> {
}

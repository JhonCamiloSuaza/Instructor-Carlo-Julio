package com.sena.crudbasic.repository;

import com.sena.crudbasic.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para la entidad Teacher.
 */
@Repository
public interface ITeacherRepository extends JpaRepository<Teacher, Integer> {

    /**
     * Buscar un docente por su nombre completo.
     */
    Optional<Teacher> findByTeacherName(String teacherName);

    /**
     * Buscar todos los docentes activos.
     */
    List<Teacher> findByActiveTrue();

    /**
     * Verificar si existe un docente con un nombre específico.
     */
    boolean existsByTeacherName(String teacherName);

    Optional<Teacher> findByExpertise(String expertise);
}

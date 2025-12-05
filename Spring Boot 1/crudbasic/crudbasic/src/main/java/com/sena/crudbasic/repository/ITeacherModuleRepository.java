package com.sena.crudbasic.repository;

import com.sena.crudbasic.model.TeacherModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para la entidad TeacherModule.
 */
@Repository
public interface ITeacherModuleRepository extends JpaRepository<TeacherModule, Integer> {

    /**
     * Buscar todos los módulos asignados a un docente por su ID.
     */
    List<TeacherModule> findByTeacher_Id(Integer teacherId);

    /**
     * Buscar todos los docentes asignados a un módulo de formación por su ID.
     */
    List<TeacherModule> findByTrainingModule_Id(Integer trainingModuleId);

    /**
     * Buscar todas las asignaciones activas.
     */
    List<TeacherModule> findByActiveTrue();

    /**
     * Buscar una asignación por ID.
     */
    Optional<TeacherModule> findById(Integer id);

    /**
     * Verificar si existe una asignación con un ID específico.
     */
    boolean existsById(Integer id);
}

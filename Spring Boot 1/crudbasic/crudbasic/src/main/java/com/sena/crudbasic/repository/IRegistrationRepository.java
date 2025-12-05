package com.sena.crudbasic.repository;

import com.sena.crudbasic.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para la entidad Registration.
 */
@Repository
public interface IRegistrationRepository extends JpaRepository<Registration, Integer> {

    /**
     * Buscar todas las matrículas de un aprendiz por su ID.
     */
    List<Registration> findByLearner_Id(Integer learnerId);

    /**
     * Buscar todas las matrículas de un módulo de formación por su ID.
     */
    List<Registration> findByTrainingModule_Id(Integer trainingModuleId);

    /**
     * Buscar todas las matrículas activas.
     */
    List<Registration> findByActiveTrue();

    /**
     * Buscar una matrícula por ID.
     */
    Optional<Registration> findById(Long id);

    /**
     * Verificar si existe una matrícula con un ID específico.
     */
    boolean existsById(Integer id);
}

package com.sena.crudbasic.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.sena.crudbasic.model.TrainingModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para la entidad TrainingModule.
 */
@Repository
public interface ITrainingModuleRepository extends JpaRepository<TrainingModule, Integer> {

    /**
     * Buscar un módulo de formación por su título.
     */
    Optional<TrainingModule> findByModuleTitle(String moduleTitle);

    /**
     * Buscar todos los módulos activos.
     */
    List<TrainingModule> findByActiveTrue();

    /**
     * Verificar si existe un módulo con un título específico.
     */
    boolean existsByModuleTitle(String moduleTitle);

    /**
     * Buscar todos los módulos cuyo costo sea menor o igual a un valor.
     */
    List<TrainingModule> findByModuleCostLessThanEqual(BigDecimal maxCost);
}

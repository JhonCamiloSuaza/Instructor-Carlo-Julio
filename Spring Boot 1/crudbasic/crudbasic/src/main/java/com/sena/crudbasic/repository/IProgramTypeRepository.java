package com.sena.crudbasic.repository;

import com.sena.crudbasic.model.ProgramType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para la entidad ProgramType.
 */
@Repository
public interface IProgramTypeRepository extends JpaRepository<ProgramType, Integer> {

    /**
     * Buscar un tipo de programa por su nombre.
     */
    Optional<ProgramType> findByTypeName(String typeName);

    /**
     * Buscar todos los tipos de programa activos.
     */
    List<ProgramType> findByActiveTrue();

    /**
     * Verificar si existe un tipo de programa con un nombre específico.
     */
    boolean existsByTypeName(String typeName);
}

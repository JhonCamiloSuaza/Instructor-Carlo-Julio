package com.sena.crudbasic.repository;

import com.sena.crudbasic.model.UserSystms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para la entidad UserSystms.
 */
@Repository
public interface IUserSystmsRepository extends JpaRepository<UserSystms, Integer> {

    /**
     * Buscar un usuario por su login.
     */
    Optional<UserSystms> findByLoginName(String loginName);

    /**
     * Verificar si existe un usuario con un login específico.
     */
    boolean existsByLoginName(String loginName);
    
    /**
     * Buscar todos los usuarios (podemos filtrar activos si lo tuvieras en BaseEntity).
     */
    List<UserSystms> findAll();
}

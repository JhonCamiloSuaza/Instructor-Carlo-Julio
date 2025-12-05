package com.sena.crudbasic.service;

import com.sena.crudbasic.dto.request.UserSystemCreateDTO;
import com.sena.crudbasic.dto.response.UserSystemResponseDTO;

import java.util.List;

public interface IUserSystmsService 
        extends GenericService<UserSystemCreateDTO, UserSystemResponseDTO, Integer> {

    // Buscar usuario por login
    UserSystemResponseDTO findByLoginName(String loginName);

    // Verificar si existe un usuario por login
    boolean existsByLoginName(String loginName);

    // Listar todos los usuarios
    List<UserSystemResponseDTO> findAllUsers();

}

package com.sena.crudbasic.service;

import java.util.List;

/**
 * Interfaz genérica para CRUD.
 *
 * @param <CreateDTO>  DTO de entrada
 * @param <ResponseDTO> DTO de salida
 * @param <ID> Tipo del ID
 */
public interface GenericService<CreateDTO, ResponseDTO, ID> {

    ResponseDTO save(CreateDTO dto);

    ResponseDTO update(ID id, CreateDTO dto);

    void delete(ID id);

    ResponseDTO findById(ID id);

    List<ResponseDTO> findAll();
}

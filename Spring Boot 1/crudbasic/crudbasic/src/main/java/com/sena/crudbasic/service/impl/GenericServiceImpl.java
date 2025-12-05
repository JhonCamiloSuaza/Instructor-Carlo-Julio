package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.service.GenericService;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación genérica para servicios CRUD.
 *
 * @param <Entity>       Entidad
 * @param <CreateDTO>    DTO de entrada
 * @param <ResponseDTO>  DTO de salida
 * @param <ID>           Tipo de ID
 */
@Transactional
public abstract class GenericServiceImpl<Entity, CreateDTO, ResponseDTO, ID>
        implements GenericService<CreateDTO, ResponseDTO, ID> {

    // REPOSITORIO QUE DEBE ENTREGAR CADA SERVICE
    protected abstract JpaRepository<Entity, ID> getRepository();

    // MAPEOS QUE DEBE IMPLEMENTAR CADA SERVICE
    protected abstract Entity mapToEntity(CreateDTO dto);

    protected abstract void updateEntity(Entity entity, CreateDTO dto);

    protected abstract ResponseDTO mapToResponse(Entity entity);

    protected abstract String getEntityName();


    // -------------------------
    // CRUD GENÉRICO
    // -------------------------

    @Override
    public ResponseDTO save(CreateDTO dto) {
        Entity entity = mapToEntity(dto);
        Entity saved = getRepository().save(entity);
        return mapToResponse(saved);
    }

    @Override
    public ResponseDTO update(ID id, CreateDTO dto) {
        Entity entity = getRepository().findById(id)
                .orElseThrow(() ->
                        new RuntimeException(getEntityName() + " no encontrado"));

        updateEntity(entity, dto);

        Entity updated = getRepository().save(entity);

        return mapToResponse(updated);
    }

    @Override
    public void delete(ID id) {
        if (!getRepository().existsById(id)) {
            throw new RuntimeException(getEntityName() + " no encontrado");
        }
        getRepository().deleteById(id);
    }

    @Override
    public ResponseDTO findById(ID id) {
        Entity entity = getRepository().findById(id)
                .orElseThrow(() ->
                        new RuntimeException(getEntityName() + " no encontrado"));

        return mapToResponse(entity);
    }

    @Override
    public List<ResponseDTO> findAll() {
        return getRepository().findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
   
   
            }
}

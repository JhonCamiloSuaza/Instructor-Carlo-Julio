package com.sena.crudbasic.service;

import com.sena.crudbasic.dto.request.TrainingModuleCreateDTO;
import com.sena.crudbasic.dto.response.TrainingModuleResponseDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ITrainingModuleService 
        extends GenericService<TrainingModuleCreateDTO, TrainingModuleResponseDTO, Integer> {

    // Buscar módulo por título
    TrainingModuleResponseDTO findByModuleTitle(String moduleTitle);

    // Lista de módulos por costo menor o igual a un valor
    List<TrainingModuleResponseDTO> findByCostLessThanEqual(BigDecimal maxCost);
}

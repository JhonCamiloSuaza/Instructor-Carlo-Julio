package com.sena.crudbasic.service;

import com.sena.crudbasic.dto.request.RegistrationCreateDTO;
import com.sena.crudbasic.dto.response.RegistrationResponseDTO;

import java.util.List;

public interface IRegistrationService extends GenericService<RegistrationCreateDTO, RegistrationResponseDTO, Integer> {

    /**
     * Listar todas las matrículas de un aprendiz por su ID
     */
    List<RegistrationResponseDTO> findByLearnerId(Integer learnerId);

    /**
     * Listar todas las matrículas de un módulo de formación por su ID
     */
    List<RegistrationResponseDTO> findByTrainingModuleId(Integer trainingModuleId);
}

package com.sena.crudbasic.service;

import com.sena.crudbasic.dto.request.FeePaymentCreateDTO;
import com.sena.crudbasic.dto.response.FeePaymentResponseDTO;

public interface IFeePaymentService 
        extends GenericService<FeePaymentCreateDTO, FeePaymentResponseDTO, Integer> {

    FeePaymentResponseDTO findByLearnerId(Integer learnerId);
 FeePaymentResponseDTO create(FeePaymentCreateDTO dto);
}




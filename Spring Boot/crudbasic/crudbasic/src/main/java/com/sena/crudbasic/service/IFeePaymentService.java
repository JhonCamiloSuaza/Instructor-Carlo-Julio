package com.sena.crudbasic.service;

import com.sena.crudbasic.dto.FeePaymentDto;
import java.util.List;

public interface IFeePaymentService {
    List<FeePaymentDto> getAll();
    FeePaymentDto getById(int id);
    FeePaymentDto save(FeePaymentDto dto);
    FeePaymentDto update(int id, FeePaymentDto dto);
    void delete(int id);
}

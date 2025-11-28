package com.sena.crudbasic.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crudbasic.model.FeePayment;
import com.sena.crudbasic.model.Registration;
import com.sena.crudbasic.dto.FeePaymentDto;
import com.sena.crudbasic.service.IFeePaymentService;
import com.sena.crudbasic.repository.IFeePaymentRepository;
import com.sena.crudbasic.repository.IRegistrationRepository;

@Service
public class FeePaymentServiceImpl implements IFeePaymentService {

    @Autowired
    private IFeePaymentRepository repository;
    
    @Autowired
    private IRegistrationRepository registrationRepository;

    @Override
    public List<FeePaymentDto> getAll() {
        return repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public FeePaymentDto getById(int id) {
        return repository.findById(id).map(this::convertToDto).orElse(null);
    }

    @Override
    public FeePaymentDto save(FeePaymentDto dto) {
        FeePayment entity = new FeePayment();
        entity.setFeeAmount(dto.getFeeAmount());
        entity.setPaidOn(dto.getPaidOn());
        if (dto.getRegistrationId() != 0) {
            Registration reg = registrationRepository.findById(dto.getRegistrationId()).orElse(null);
            entity.setRegistration(reg);
        }
        return convertToDto(repository.save(entity));
    }

    @Override
    public FeePaymentDto update(int id, FeePaymentDto dto) {
        return repository.findById(id).map(existing -> {
            existing.setFeeAmount(dto.getFeeAmount());
            existing.setPaidOn(dto.getPaidOn());
            if (dto.getRegistrationId() != 0) {
                Registration reg = registrationRepository.findById(dto.getRegistrationId()).orElse(null);
                existing.setRegistration(reg);
            }
            return convertToDto(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    private FeePaymentDto convertToDto(FeePayment feePayment) {
        FeePaymentDto dto = new FeePaymentDto();
        dto.setId(feePayment.getId());
        dto.setFeeAmount(feePayment.getFeeAmount());
        dto.setPaidOn(feePayment.getPaidOn());
        if (feePayment.getRegistration() != null) {
            dto.setRegistrationId(feePayment.getRegistration().getId());
        }
        return dto;
    }
}


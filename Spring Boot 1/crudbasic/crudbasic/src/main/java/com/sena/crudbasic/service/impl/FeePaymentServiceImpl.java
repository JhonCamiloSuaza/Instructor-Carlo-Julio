package com.sena.crudbasic.service.impl;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sena.crudbasic.dto.request.FeePaymentCreateDTO;
import com.sena.crudbasic.dto.response.FeePaymentResponseDTO;
import com.sena.crudbasic.model.FeePayment;
import com.sena.crudbasic.model.Registration;
import com.sena.crudbasic.repository.IRegistrationRepository;
import com.sena.crudbasic.repository.IFeePaymentRepository;
import com.sena.crudbasic.service.IFeePaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FeePaymentServiceImpl 
        extends GenericServiceImpl<FeePayment, FeePaymentCreateDTO, FeePaymentResponseDTO, Integer>
        implements IFeePaymentService {

    private final IFeePaymentRepository feePaymentRepository;
    private final IRegistrationRepository registrationRepository;

    // Implementación del método create de la interfaz
@Override
@Transactional
public FeePaymentResponseDTO create(FeePaymentCreateDTO dto) {
    FeePayment entity = mapToEntity(dto); // Convierte DTO a entidad
    FeePayment saved = feePaymentRepository.save(entity); // Guarda en BD
    return mapToResponse(saved); // Convierte a ResponseDTO
}
    @Override
    public FeePaymentResponseDTO findByLearnerId(Integer learnerId) {
        List<FeePayment> list = feePaymentRepository.findByLearner_Id(learnerId);

        if (list.isEmpty()) {
            throw new RuntimeException("No hay pagos registrados para el aprendiz con ID: " + learnerId);
        }

        return mapToResponse(list.get(0));
    }

    @Override
    protected FeePayment mapToEntity(FeePaymentCreateDTO dto) {
        Registration registration = registrationRepository.findById(dto.getRegistrationId())
                .orElseThrow(() -> new RuntimeException("Registro no encontrado con ID: " + dto.getRegistrationId()));

        return FeePayment.builder()
                .feeAmount(dto.getFeeAmount())
                .paidOn(dto.getPaidOn())
                .registration(registration)
                .build();
    }

    @Override
    protected void updateEntity(FeePayment entity, FeePaymentCreateDTO dto) {
        Registration registration = registrationRepository.findById(dto.getRegistrationId())
                .orElseThrow(() -> new RuntimeException("Registro no encontrado con ID: " + dto.getRegistrationId()));

        entity.setFeeAmount(dto.getFeeAmount());
        entity.setPaidOn(dto.getPaidOn());
        entity.setRegistration(registration);
    }

    @Override
    protected FeePaymentResponseDTO mapToResponse(FeePayment fee) {
        return FeePaymentResponseDTO.builder()
                .id(fee.getId())
                .learnerId(fee.getRegistration().getLearner().getId())
                .amountPaid(fee.getFeeAmount())
                .paymentDate(fee.getPaidOn().atStartOfDay().toInstant(java.time.ZoneOffset.UTC))
                .paymentMethod("Efectivo") // si deseas agregar más lógica
                .createdAt(fee.getCreatedAt())
                .updatedAt(fee.getUpdatedAt())
                .build();
    }

    @Override
    protected JpaRepository<FeePayment, Integer> getRepository() {
        return feePaymentRepository;
    }

    @Override
    protected String getEntityName() {
        return "Fee Payment";
    }
}

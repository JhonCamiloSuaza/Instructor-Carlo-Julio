package com.sena.crudbasic.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeePaymentCreateDTO {

    @NotNull(message = "El monto del pago es obligatorio")
    @Positive(message = "El monto del pago debe ser positivo")
    private BigDecimal feeAmount;

    @NotNull(message = "La fecha de pago es obligatoria")
    private LocalDate paidOn;

    @NotNull(message = "El ID del registro es obligatorio")
    private Long registrationId;
}

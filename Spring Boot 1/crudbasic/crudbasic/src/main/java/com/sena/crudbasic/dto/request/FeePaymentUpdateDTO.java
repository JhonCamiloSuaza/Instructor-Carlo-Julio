package com.sena.crudbasic.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeePaymentUpdateDTO {

    @NotNull(message = "El monto del pago es obligatorio")
    @Positive(message = "El monto del pago debe ser positivo")
    private BigDecimal feeAmount;

    @NotNull(message = "La fecha de pago es obligatoria")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate paidOn;

    @NotNull(message = "El ID del registro es obligatorio")
    private Integer registrationId;
}

package com.sena.crudbasic.dto.response;

import java.math.BigDecimal;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonView;
import com.sena.crudbasic.dto.view.JsonViews;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeePaymentResponseDTO {

    @JsonView(JsonViews.Resumen.class)
    private Integer id;

    @JsonView(JsonViews.Resumen.class)
    private Integer learnerId;

    @JsonView(JsonViews.Resumen.class)
    private BigDecimal amountPaid;

    @JsonView(JsonViews.Resumen.class)
    private Instant paymentDate;

    @JsonView(JsonViews.Detalle.class)
    private String paymentMethod;

    @JsonView(JsonViews.Detalle.class)
    private Instant createdAt;

    @JsonView(JsonViews.Detalle.class)
    private Instant updatedAt;
}

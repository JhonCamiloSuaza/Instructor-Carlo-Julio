package com.sena.crudbasic.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fee_payment")
public class FeePayment extends BaseEntity {

    @Column(name = "fee_amount", nullable = false)
    private BigDecimal feeAmount;

    @Column(name = "paid_on", nullable = false)
    private LocalDate paidOn;

    @ManyToOne
    @JoinColumn(name = "id_registration", nullable = false)
    private Registration registration;

    public Instant getCreatedAt() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCreatedAt'");
    }

    public Instant getUpdatedAt() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

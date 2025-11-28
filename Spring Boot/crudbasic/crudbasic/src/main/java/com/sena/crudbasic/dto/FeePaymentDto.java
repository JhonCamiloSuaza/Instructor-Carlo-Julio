package com.sena.crudbasic.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FeePaymentDto {

    private int id;
    private BigDecimal feeAmount;
    private LocalDate paidOn;
    private int registrationId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public LocalDate getPaidOn() {
        return paidOn;
    }

    public void setPaidOn(LocalDate paidOn) {
        this.paidOn = paidOn;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }
}

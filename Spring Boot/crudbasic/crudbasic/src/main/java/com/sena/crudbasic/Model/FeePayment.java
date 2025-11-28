  package com.sena.crudbasic.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "fee_payment")
public class FeePayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fee_payment")
    private int id;

    @Column(name = "fee_amount")
    private BigDecimal feeAmount;

    @Column(name = "paid_on")
    private LocalDate paidOn;

    @ManyToOne
    @JoinColumn(name = "id_registration")
    private Registration registration;

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

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }


    
    

    
}

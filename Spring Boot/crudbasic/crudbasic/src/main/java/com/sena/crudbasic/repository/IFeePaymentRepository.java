package com.sena.crudbasic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crudbasic.model.FeePayment;

public interface IFeePaymentRepository extends JpaRepository<FeePayment, Integer> {
    
}

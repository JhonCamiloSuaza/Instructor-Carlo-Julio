package com.sena.crudbasic.repository;

import com.sena.crudbasic.model.FeePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFeePaymentRepository extends JpaRepository<FeePayment, Integer> {

    List<FeePayment> findByLearner_Id(Integer learnerId);

    List<FeePayment> findByActiveTrue();
}

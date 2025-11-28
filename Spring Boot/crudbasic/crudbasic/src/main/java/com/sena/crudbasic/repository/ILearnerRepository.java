package com.sena.crudbasic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crudbasic.model.Learner;

public interface ILearnerRepository extends JpaRepository<Learner, Integer> {
}

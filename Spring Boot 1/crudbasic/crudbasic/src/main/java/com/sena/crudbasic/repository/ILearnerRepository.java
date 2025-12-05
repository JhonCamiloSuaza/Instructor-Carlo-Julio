package com.sena.crudbasic.repository;

import com.sena.crudbasic.model.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILearnerRepository extends JpaRepository<Learner, Integer> {

    boolean existsByLearnerName(String learnerName);

    boolean existsBySystemUser_Id(Integer userId);

}

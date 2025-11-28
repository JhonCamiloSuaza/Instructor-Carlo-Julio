package com.sena.crudbasic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crudbasic.model.TrainingModule;

public interface ITrainingModuleRepository extends JpaRepository<TrainingModule, Integer> {
}

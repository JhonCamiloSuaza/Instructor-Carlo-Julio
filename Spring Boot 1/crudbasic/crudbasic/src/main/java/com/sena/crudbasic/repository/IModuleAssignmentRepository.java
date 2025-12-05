package com.sena.crudbasic.repository;

import com.sena.crudbasic.model.ModuleAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IModuleAssignmentRepository extends JpaRepository<ModuleAssignment, Integer> {

    List<ModuleAssignment> findByProgramType_Id(Integer programTypeId);

    List<ModuleAssignment> findByTrainingModule_Id(Integer trainingModuleId);

    List<ModuleAssignment> findByActiveTrue();

    boolean existsById(Integer id);
}

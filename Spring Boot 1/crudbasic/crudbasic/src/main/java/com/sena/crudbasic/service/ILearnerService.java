package com.sena.crudbasic.service;

import com.sena.crudbasic.dto.request.LearnerCreateDTO;
import com.sena.crudbasic.model.Learner;

import java.util.List;

public interface ILearnerService {

    List<Learner> findAll();

    Learner findById(Integer id);

    Learner create(LearnerCreateDTO dto);

    Learner update(Integer id, LearnerCreateDTO dto);

    void delete(Integer id);
}

package com.sena.crudbasic.service;


import java.util.List;

import com.sena.crudbasic.dto.LearnerDto;

public interface ILearnerService {
    List<LearnerDto> getAll();
    LearnerDto getById(int id);
    LearnerDto save(LearnerDto dto);
    LearnerDto update(int id, LearnerDto dto);
    void delete(int id);
}

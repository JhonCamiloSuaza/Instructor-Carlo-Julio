package com.sena.crudbasic.service;

import com.sena.crudbasic.dto.TrainingModuleDto;
import java.util.List;

public interface ITrainingModuleService {
    List<TrainingModuleDto> getAll();
    TrainingModuleDto getById(int id);
    TrainingModuleDto save(TrainingModuleDto dto);
    TrainingModuleDto update(int id, TrainingModuleDto dto);
    void delete(int id);
}

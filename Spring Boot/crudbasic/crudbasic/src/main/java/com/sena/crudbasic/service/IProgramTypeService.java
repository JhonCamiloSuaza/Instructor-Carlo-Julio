package com.sena.crudbasic.service;

import com.sena.crudbasic.dto.ProgramTypeDto;
import java.util.List;

public interface IProgramTypeService {
    List<ProgramTypeDto> getAll();
    ProgramTypeDto getById(int id);
    ProgramTypeDto save(ProgramTypeDto dto);
    ProgramTypeDto update(int id, ProgramTypeDto dto);
    void delete(int id);
}

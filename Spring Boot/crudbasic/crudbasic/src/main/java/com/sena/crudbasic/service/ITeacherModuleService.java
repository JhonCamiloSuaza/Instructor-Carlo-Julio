package com.sena.crudbasic.service;

import com.sena.crudbasic.dto.TeacherModuleDto;
import java.util.List;

public interface ITeacherModuleService {
    List<TeacherModuleDto> getAll();
    TeacherModuleDto getById(int id);
    TeacherModuleDto save(TeacherModuleDto dto);
    TeacherModuleDto update(int id, TeacherModuleDto dto);
    void delete(int id);
}

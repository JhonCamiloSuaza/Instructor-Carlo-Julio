package com.sena.crudbasic.service;

import com.sena.crudbasic.dto.TeacherDto;
import java.util.List;

public interface ITeacherService {
    List<TeacherDto> getAll();
    TeacherDto getById(int id);
    TeacherDto save(TeacherDto dto);
    TeacherDto update(int id, TeacherDto dto);
    void delete(int id);
}

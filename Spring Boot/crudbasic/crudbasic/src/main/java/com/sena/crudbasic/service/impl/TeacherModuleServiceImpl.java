package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.model.Teacher_Module;
import com.sena.crudbasic.dto.TeacherModuleDto;
import com.sena.crudbasic.repository.ITeacherModuleRepository;
import com.sena.crudbasic.repository.ITeacherRepository;
import com.sena.crudbasic.repository.ITrainingModuleRepository;
import com.sena.crudbasic.service.ITeacherModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherModuleServiceImpl implements ITeacherModuleService {

    @Autowired
    private ITeacherModuleRepository repository;

    @Autowired
    private ITeacherRepository teacherRepository;

    @Autowired
    private ITrainingModuleRepository moduleRepository;

    @Override
    public List<TeacherModuleDto> getAll() {
        return repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public TeacherModuleDto getById(int id) {
        return repository.findById(id).map(this::convertToDto).orElse(null);
    }

    @Override
    public TeacherModuleDto save(TeacherModuleDto dto) {
        Teacher_Module tm = new Teacher_Module();
        tm.setTeacher(teacherRepository.findById(dto.getTeacherId()).orElse(null));
        tm.setTrainingModule(moduleRepository.findById(dto.getTrainingModuleId()).orElse(null));
        return convertToDto(repository.save(tm));
    }

    @Override
    public TeacherModuleDto update(int id, TeacherModuleDto dto) {
        return repository.findById(id).map(existing -> {
            existing.setTeacher(teacherRepository.findById(dto.getTeacherId()).orElse(null));
            existing.setTrainingModule(moduleRepository.findById(dto.getTrainingModuleId()).orElse(null));
            return convertToDto(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    private TeacherModuleDto convertToDto(Teacher_Module tm) {
        TeacherModuleDto dto = new TeacherModuleDto();
        dto.setId(tm.getId());
        dto.setTeacherId(tm.getTeacher() != null ? tm.getTeacher().getId() : 0);
        dto.setTrainingModuleId(tm.getTrainingModule() != null ? tm.getTrainingModule().getId() : 0);
        return dto;
    }
}

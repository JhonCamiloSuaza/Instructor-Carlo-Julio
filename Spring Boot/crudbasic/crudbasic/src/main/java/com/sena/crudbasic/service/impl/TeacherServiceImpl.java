package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.model.Teacher;
import com.sena.crudbasic.dto.TeacherDto;
import com.sena.crudbasic.repository.ITeacherRepository;
import com.sena.crudbasic.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private ITeacherRepository repository;

    @Override
    public List<TeacherDto> getAll() {
        return repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public TeacherDto getById(int id) {
        return repository.findById(id).map(this::convertToDto).orElse(null);
    }

    @Override
    public TeacherDto save(TeacherDto dto) {
        Teacher teacher = new Teacher();
        teacher.setTeacherName(dto.getTeacherName());
        teacher.setExpertise(dto.getExpertise());
        return convertToDto(repository.save(teacher));
    }

    @Override
    public TeacherDto update(int id, TeacherDto dto) {
        return repository.findById(id).map(existing -> {
            existing.setTeacherName(dto.getTeacherName());
            existing.setExpertise(dto.getExpertise());
            return convertToDto(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    private TeacherDto convertToDto(Teacher teacher) {
        TeacherDto dto = new TeacherDto();
        dto.setId(teacher.getId());
        dto.setTeacherName(teacher.getTeacherName());
        dto.setExpertise(teacher.getExpertise());
        return dto;
    }

}

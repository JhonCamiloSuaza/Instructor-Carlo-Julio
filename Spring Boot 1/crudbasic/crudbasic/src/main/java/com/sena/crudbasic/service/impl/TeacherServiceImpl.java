package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.dto.request.TeacherCreateDTO;
import com.sena.crudbasic.dto.response.TeacherResponseDTO;
import com.sena.crudbasic.model.Teacher;
import com.sena.crudbasic.repository.ITeacherRepository;
import com.sena.crudbasic.service.ITeacherService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeacherServiceImpl 
        extends GenericServiceImpl<Teacher, TeacherCreateDTO, TeacherResponseDTO, Integer>
        implements ITeacherService {

    private final ITeacherRepository teacherRepository;

    @Override
    public TeacherResponseDTO findByTeacherName(String teacherName) {
        Teacher teacher = teacherRepository.findByTeacherName(teacherName)
                .orElseThrow(() -> new RuntimeException(
                        "Docente no encontrado con el nombre: " + teacherName));
        return mapToResponse(teacher);
    }

    @Override
    public List<TeacherResponseDTO> findByExpertise(String expertise) {
        return teacherRepository.findByExpertise(expertise)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TeacherResponseDTO> findByActiveTrue() {
        // Como tu entidad no tiene campo 'active', retornamos todos los docentes
        return teacherRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    protected Teacher mapToEntity(TeacherCreateDTO dto) {
        return Teacher.builder()
                .teacherName(dto.getTeacherName())
                .expertise(dto.getExpertise())
                .build();
    }

    @Override
    protected void updateEntity(Teacher entity, TeacherCreateDTO dto) {
        entity.setTeacherName(dto.getTeacherName());
        entity.setExpertise(dto.getExpertise());
    }

    @Override
    protected TeacherResponseDTO mapToResponse(Teacher teacher) {
        return TeacherResponseDTO.builder()
                .id(teacher.getId())
                .teacherName(teacher.getTeacherName())
                .expertise(teacher.getExpertise())
                .createdAt(teacher.getCreatedAt())
                .updatedAt(teacher.getUpdatedAt())
                .build();
    }

    @Override
    protected JpaRepository<Teacher, Integer> getRepository() {
        return teacherRepository;
    }

    @Override
    protected String getEntityName() {
        return "Docente";
    }
}

package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.dto.request.TeacherModuleCreateDTO;
import com.sena.crudbasic.dto.response.TeacherModuleResponseDTO;
import com.sena.crudbasic.model.Teacher;
import com.sena.crudbasic.model.TeacherModule;
import com.sena.crudbasic.model.TrainingModule;
import com.sena.crudbasic.repository.ITeacherModuleRepository;
import com.sena.crudbasic.repository.ITeacherRepository;
import com.sena.crudbasic.repository.ITrainingModuleRepository;
import com.sena.crudbasic.service.ITeacherModuleService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeacherModuleServiceImpl
        extends GenericServiceImpl<TeacherModule, TeacherModuleCreateDTO, TeacherModuleResponseDTO, Integer>
        implements ITeacherModuleService {

    private final ITeacherModuleRepository teacherModuleRepository;
    private final ITeacherRepository teacherRepository;
    private final ITrainingModuleRepository trainingModuleRepository;

    @Override
    public boolean existsByTeacherAndModule(Integer teacherId, Integer trainingModuleId) {
        return teacherModuleRepository.findByTeacher_Id(teacherId)
                .stream()
                .anyMatch(tm -> tm.getTrainingModule().getId().equals(trainingModuleId));
    }

    @Override
    protected TeacherModule mapToEntity(TeacherModuleCreateDTO dto) {
        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Docente no encontrado con ID: " + dto.getTeacherId()));

        TrainingModule module = trainingModuleRepository.findById(dto.getTrainingModuleId())
                .orElseThrow(() -> new RuntimeException("Módulo de formación no encontrado con ID: " + dto.getTrainingModuleId()));

        // Validar que no exista la misma asignación
        if (existsByTeacherAndModule(dto.getTeacherId(), dto.getTrainingModuleId())) {
            throw new RuntimeException("Esta asignación ya existe.");
        }

        return TeacherModule.builder()
                .teacher(teacher)
                .trainingModule(module)
                .build();
    }

    @Override
    protected void updateEntity(TeacherModule entity, TeacherModuleCreateDTO dto) {
        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Docente no encontrado con ID: " + dto.getTeacherId()));

        TrainingModule module = trainingModuleRepository.findById(dto.getTrainingModuleId())
                .orElseThrow(() -> new RuntimeException("Módulo de formación no encontrado con ID: " + dto.getTrainingModuleId()));

        entity.setTeacher(teacher);
        entity.setTrainingModule(module);
    }

    @Override
    protected TeacherModuleResponseDTO mapToResponse(TeacherModule entity) {
        return TeacherModuleResponseDTO.builder()
                .id(entity.getId())
                .teacherId(entity.getTeacher().getId())
                .trainingModuleId(entity.getTrainingModule().getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    protected JpaRepository<TeacherModule, Integer> getRepository() {
        return teacherModuleRepository;
    }

    @Override
    protected String getEntityName() {
        return "Asignación de Docente a Módulo";
    }
}

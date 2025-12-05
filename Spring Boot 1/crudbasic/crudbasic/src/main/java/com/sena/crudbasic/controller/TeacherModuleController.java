package com.sena.crudbasic.controller;

import com.sena.crudbasic.dto.request.TeacherModuleCreateDTO;
import com.sena.crudbasic.dto.response.TeacherModuleResponseDTO;
import com.sena.crudbasic.service.ITeacherModuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher-modules")
@RequiredArgsConstructor
public class TeacherModuleController {

    private final ITeacherModuleService teacherModuleService;

    // Crear asignación docente-módulo
    @PostMapping
    public ResponseEntity<TeacherModuleResponseDTO> create(@Valid @RequestBody TeacherModuleCreateDTO dto) {
        TeacherModuleResponseDTO response = teacherModuleService.save(dto);
        return ResponseEntity.ok(response);
    }

    // Actualizar asignación por ID
    @PutMapping("/{id}")
    public ResponseEntity<TeacherModuleResponseDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody TeacherModuleCreateDTO dto) {
        TeacherModuleResponseDTO response = teacherModuleService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    // Eliminar asignación por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        teacherModuleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener todas las asignaciones
    @GetMapping
    public ResponseEntity<List<TeacherModuleResponseDTO>> findAll() {
        List<TeacherModuleResponseDTO> list = teacherModuleService.findAll();
        return ResponseEntity.ok(list);
    }

    // Obtener asignación por ID
    @GetMapping("/{id}")
    public ResponseEntity<TeacherModuleResponseDTO> findById(@PathVariable Integer id) {
        TeacherModuleResponseDTO response = teacherModuleService.findById(id);
        return ResponseEntity.ok(response);
    }

    // Buscar todas las asignaciones de un docente
    @GetMapping("/by-teacher/{teacherId}")
    public ResponseEntity<List<TeacherModuleResponseDTO>> findByTeacher(@PathVariable Integer teacherId) {
        List<TeacherModuleResponseDTO> list = teacherModuleService.findAll()
                .stream()
                .filter(tm -> tm.getTeacherId().equals(teacherId))
                .toList();
        return ResponseEntity.ok(list);
    }

    // Buscar todas las asignaciones de un módulo de formación
    @GetMapping("/by-module/{moduleId}")
    public ResponseEntity<List<TeacherModuleResponseDTO>> findByModule(@PathVariable Integer moduleId) {
        List<TeacherModuleResponseDTO> list = teacherModuleService.findAll()
                .stream()
                .filter(tm -> tm.getTrainingModuleId().equals(moduleId))
                .toList();
        return ResponseEntity.ok(list);
    }
}

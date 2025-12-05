package com.sena.crudbasic.controller;

import com.sena.crudbasic.dto.request.TeacherCreateDTO;
import com.sena.crudbasic.dto.response.TeacherResponseDTO;
import com.sena.crudbasic.service.ITeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final ITeacherService teacherService;

    // Crear docente
    @PostMapping
    public ResponseEntity<TeacherResponseDTO> create(@Valid @RequestBody TeacherCreateDTO dto) {
        TeacherResponseDTO response = teacherService.save(dto);
        return ResponseEntity.ok(response);
    }

    // Actualizar docente por ID
    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody TeacherCreateDTO dto) {
        TeacherResponseDTO response = teacherService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    // Eliminar docente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener todos los docentes
    @GetMapping
    public ResponseEntity<List<TeacherResponseDTO>> findAll() {
        List<TeacherResponseDTO> teachers = teacherService.findAll();
        return ResponseEntity.ok(teachers);
    }

    // Obtener docente por ID
    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> findById(@PathVariable Integer id) {
        TeacherResponseDTO teacher = teacherService.findById(id);
        return ResponseEntity.ok(teacher);
    }

    // Buscar docente por nombre
    @GetMapping("/search/name")
    public ResponseEntity<TeacherResponseDTO> findByName(@RequestParam String teacherName) {
        TeacherResponseDTO teacher = teacherService.findByTeacherName(teacherName);
        return ResponseEntity.ok(teacher);
    }

    // Buscar docentes por especialidad
    @GetMapping("/search/expertise")
    public ResponseEntity<List<TeacherResponseDTO>> findByExpertise(@RequestParam String expertise) {
        List<TeacherResponseDTO> teachers = teacherService.findByExpertise(expertise);
        return ResponseEntity.ok(teachers);
    }
}

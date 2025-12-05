package com.sena.crudbasic.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.sena.crudbasic.dto.request.ModuleAssignmentCreateDTO;
import com.sena.crudbasic.dto.response.ModuleAssignmentResponseDTO;
import com.sena.crudbasic.dto.view.JsonViews;
import com.sena.crudbasic.service.ModuleAssignmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/module-assignments")
@RequiredArgsConstructor
@Tag(name = "ModuleAssignments", description = "API para gestión de asignaciones de módulos")
public class ModuleAssignmentController {

    private final ModuleAssignmentService moduleAssignmentService;

    // Obtener todas las asignaciones
    @GetMapping
    @JsonView(JsonViews.Resumen.class)
    public ResponseEntity<List<ModuleAssignmentResponseDTO>> getAll() {
        return ResponseEntity.ok(moduleAssignmentService.findAll());
    }

    // Obtener asignación por ID
    @GetMapping("/{id}")
    @JsonView(JsonViews.Detalle.class)
    public ResponseEntity<ModuleAssignmentResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(moduleAssignmentService.findById(id));
    }

    // Crear asignación
    @PostMapping
    @JsonView(JsonViews.Detalle.class)
    public ResponseEntity<ModuleAssignmentResponseDTO> create(
            @Valid @RequestBody ModuleAssignmentCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(moduleAssignmentService.save(dto));
    }

    // Actualizar asignación
    @PutMapping("/{id}")
    @JsonView(JsonViews.Detalle.class)
    public ResponseEntity<ModuleAssignmentResponseDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody ModuleAssignmentCreateDTO dto) {
        return ResponseEntity.ok(moduleAssignmentService.update(id, dto));
    }

    // Eliminar asignación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        moduleAssignmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener asignaciones por tipo de programa
    @GetMapping("/program-type/{programTypeId}")
    @JsonView(JsonViews.Resumen.class)
    public ResponseEntity<List<ModuleAssignmentResponseDTO>> getByProgramType(
            @PathVariable Integer programTypeId) {
        return ResponseEntity.ok(moduleAssignmentService.findByProgramTypeId(programTypeId));
    }

    // Obtener asignaciones por módulo de entrenamiento
    @GetMapping("/training-module/{trainingModuleId}")
    @JsonView(JsonViews.Resumen.class)
    public ResponseEntity<List<ModuleAssignmentResponseDTO>> getByTrainingModule(
            @PathVariable Integer trainingModuleId) {
        return ResponseEntity.ok(moduleAssignmentService.findByTrainingModuleId(trainingModuleId));
    }
}

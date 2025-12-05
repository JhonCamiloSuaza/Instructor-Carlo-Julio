package com.sena.crudbasic.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.sena.crudbasic.dto.request.ProgramTypeCreateDTO;
import com.sena.crudbasic.dto.response.ProgramTypeResponseDTO;
import com.sena.crudbasic.dto.view.JsonViews;
import com.sena.crudbasic.service.IProgramTypeService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/program-types")
@RequiredArgsConstructor
@Tag(name = "Program Types", description = "API para gestión de tipos de programa")
public class ProgramTypeController {

    private final IProgramTypeService programTypeService;

    // Obtener todos los tipos de programa
    @GetMapping
    @JsonView(JsonViews.Resumen.class)
    public ResponseEntity<List<ProgramTypeResponseDTO>> getAllProgramTypes() {
        return ResponseEntity.ok(programTypeService.findAll());
    }

    // Obtener tipo de programa por ID
    @GetMapping("/{id}")
    @JsonView(JsonViews.Detalle.class)
    public ResponseEntity<ProgramTypeResponseDTO> getProgramTypeById(@PathVariable Integer id) {
        return ResponseEntity.ok(programTypeService.findById(id));
    }

    // Crear nuevo tipo de programa
    @PostMapping
    @JsonView(JsonViews.Detalle.class)
    public ResponseEntity<ProgramTypeResponseDTO> createProgramType(
            @Valid @RequestBody ProgramTypeCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(programTypeService.save(dto));
    }

    // Actualizar tipo de programa
    @PutMapping("/{id}")
    @JsonView(JsonViews.Detalle.class)
    public ResponseEntity<ProgramTypeResponseDTO> updateProgramType(
            @PathVariable Integer id,
            @Valid @RequestBody ProgramTypeCreateDTO dto) {
        return ResponseEntity.ok(programTypeService.update(id, dto));
    }

    // Eliminar tipo de programa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgramType(@PathVariable Integer id) {
        programTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

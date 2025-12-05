package com.sena.crudbasic.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.sena.crudbasic.dto.request.LearnerCreateDTO;
import com.sena.crudbasic.dto.response.LearnerResponseDTO;
import com.sena.crudbasic.dto.view.JsonViews;
import com.sena.crudbasic.model.Learner;
import com.sena.crudbasic.service.ILearnerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/learners")
@RequiredArgsConstructor
@Tag(name = "Learners", description = "API para gestión de aprendices")
public class LearnerController {

    private final ILearnerService learnerService;

 
    @GetMapping
    @JsonView(JsonViews.Resumen.class)
    public ResponseEntity<List<LearnerResponseDTO>> getAllLearners() {
        List<LearnerResponseDTO> list = learnerService.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    // Obtener aprendiz por ID
    @GetMapping("/{id}")
    @JsonView(JsonViews.Detalle.class)
    public ResponseEntity<LearnerResponseDTO> getLearnerById(@PathVariable Integer id) {
        Learner learner = learnerService.findById(id);
        return ResponseEntity.ok(mapToResponse(learner));
    }

    // Crear aprendiz
    @PostMapping
    @JsonView(JsonViews.Detalle.class)
    public ResponseEntity<LearnerResponseDTO> createLearner(@Valid @RequestBody LearnerCreateDTO dto) {
        Learner learner = learnerService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToResponse(learner));
    }

    // Actualizar aprendiz
    @PutMapping("/{id}")
    @JsonView(JsonViews.Detalle.class)
    public ResponseEntity<LearnerResponseDTO> updateLearner(
            @PathVariable Integer id,
            @Valid @RequestBody LearnerCreateDTO dto) {
        Learner learner = learnerService.update(id, dto);
        return ResponseEntity.ok(mapToResponse(learner));
    }

    // Eliminar aprendiz
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLearner(@PathVariable Integer id) {
        learnerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ---- Método auxiliar para mapear a DTO ----
    private LearnerResponseDTO mapToResponse(Learner learner) {
        return LearnerResponseDTO.builder()
                .id(learner.getId())
                .fullName(learner.getLearnerName())
                .systemUserId(learner.getSystemUser().getId())
                .createdAt(learner.getCreatedAt())
                .updatedAt(learner.getUpdatedAt())
                .build();
    }
}

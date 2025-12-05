package com.sena.crudbasic.controller;

import com.sena.crudbasic.dto.request.TrainingModuleCreateDTO;
import com.sena.crudbasic.dto.response.TrainingModuleResponseDTO;
import com.sena.crudbasic.service.ITrainingModuleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/training-modules")
@RequiredArgsConstructor
public class TrainingModuleController {

    private final ITrainingModuleService trainingModuleService;

    // Crear módulo
    @PostMapping
    public ResponseEntity<TrainingModuleResponseDTO> create(@Valid @RequestBody TrainingModuleCreateDTO dto) {
        TrainingModuleResponseDTO response = trainingModuleService.save(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Actualizar módulo
    @PutMapping("/{id}")
    public ResponseEntity<TrainingModuleResponseDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody TrainingModuleCreateDTO dto) {
        TrainingModuleResponseDTO response = trainingModuleService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    // Eliminar módulo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        trainingModuleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener módulo por ID
    @GetMapping("/{id}")
    public ResponseEntity<TrainingModuleResponseDTO> getById(@PathVariable Integer id) {
        TrainingModuleResponseDTO response = trainingModuleService.findById(id);
        return ResponseEntity.ok(response);
    }

    // Obtener módulo por título
    @GetMapping("/title/{title}")
    public ResponseEntity<TrainingModuleResponseDTO> getByTitle(@PathVariable String title) {
        TrainingModuleResponseDTO response = trainingModuleService.findByModuleTitle(title);
        return ResponseEntity.ok(response);
    }

    // Listar todos los módulos
    @GetMapping
    public ResponseEntity<List<TrainingModuleResponseDTO>> getAll() {
        List<TrainingModuleResponseDTO> list = trainingModuleService.findAll();
        return ResponseEntity.ok(list);
    }

    // Listar módulos por costo máximo
    @GetMapping("/cost")
    public ResponseEntity<List<TrainingModuleResponseDTO>> getByCost(@RequestParam BigDecimal maxCost) {
        List<TrainingModuleResponseDTO> list = trainingModuleService.findByCostLessThanEqual(maxCost);
        return ResponseEntity.ok(list);
    }
}

package com.sena.crudbasic.controller;

import com.sena.crudbasic.dto.request.RegistrationCreateDTO;
import com.sena.crudbasic.dto.response.RegistrationResponseDTO;
import com.sena.crudbasic.service.IRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final IRegistrationService registrationService;

    // Crear un nuevo registro
    @PostMapping
    public ResponseEntity<RegistrationResponseDTO> create(@Validated @RequestBody RegistrationCreateDTO dto) {
        RegistrationResponseDTO response = registrationService.save(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Actualizar un registro existente
    @PutMapping("/{id}")
    public ResponseEntity<RegistrationResponseDTO> update(
            @PathVariable Integer id,
            @Validated @RequestBody RegistrationCreateDTO dto) {
        RegistrationResponseDTO response = registrationService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    // Eliminar un registro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        registrationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener un registro por ID
    @GetMapping("/{id}")
    public ResponseEntity<RegistrationResponseDTO> findById(@PathVariable Integer id) {
        RegistrationResponseDTO response = registrationService.findById(id);
        return ResponseEntity.ok(response);
    }

    // Listar todos los registros
    @GetMapping
    public ResponseEntity<List<RegistrationResponseDTO>> findAll() {
        List<RegistrationResponseDTO> responseList = registrationService.findAll();
        return ResponseEntity.ok(responseList);
    }

    // Listar registros por ID de aprendiz
    @GetMapping("/learner/{learnerId}")
    public ResponseEntity<List<RegistrationResponseDTO>> findByLearnerId(@PathVariable Integer learnerId) {
        List<RegistrationResponseDTO> responseList = registrationService.findByLearnerId(learnerId);
        return ResponseEntity.ok(responseList);
    }

    // Listar registros por ID de módulo de formación
    @GetMapping("/module/{trainingModuleId}")
    public ResponseEntity<List<RegistrationResponseDTO>> findByTrainingModuleId(@PathVariable Integer trainingModuleId) {
        List<RegistrationResponseDTO> responseList = registrationService.findByTrainingModuleId(trainingModuleId);
        return ResponseEntity.ok(responseList);
    }
}

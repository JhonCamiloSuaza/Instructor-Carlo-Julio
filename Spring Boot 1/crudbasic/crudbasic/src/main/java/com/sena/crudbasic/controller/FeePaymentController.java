package com.sena.crudbasic.controller;

import com.sena.crudbasic.dto.request.FeePaymentCreateDTO;
import com.sena.crudbasic.dto.response.FeePaymentResponseDTO;
import com.sena.crudbasic.dto.view.JsonViews;
import com.sena.crudbasic.service.IFeePaymentService;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fee-payments")
@RequiredArgsConstructor
@Tag(name = "Fee Payments", description = "API para gestión de pagos de matrícula")
public class FeePaymentController {

    private final IFeePaymentService feePaymentService;


    @GetMapping
    @JsonView(JsonViews.Resumen.class)
    public ResponseEntity<List<FeePaymentResponseDTO>> getAllPayments() {
        return ResponseEntity.ok(feePaymentService.findAll());
    }

    // Obtener pago por ID
    @GetMapping("/{id}")
    @JsonView(JsonViews.Detalle.class)
    public ResponseEntity<FeePaymentResponseDTO> getPaymentById(@PathVariable Integer id) {
        return ResponseEntity.ok(feePaymentService.findById(id));
    }

    // Crear nuevo pago
    @PostMapping
    @JsonView(JsonViews.Detalle.class)
    public ResponseEntity<FeePaymentResponseDTO> createPayment(
            @Valid @RequestBody FeePaymentCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(feePaymentService.create(dto));
    }

    // Actualizar pago existente
    @PutMapping("/{id}")
    @JsonView(JsonViews.Detalle.class)
    public ResponseEntity<FeePaymentResponseDTO> updatePayment(
            @PathVariable Integer id,
            @Valid @RequestBody FeePaymentCreateDTO dto) {
        return ResponseEntity.ok(feePaymentService.update(id, dto));
    }

    // Eliminar pago
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Integer id) {
        feePaymentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener pagos por ID de aprendiz
    @GetMapping("/learner/{learnerId}")
    @JsonView(JsonViews.Resumen.class)
    public ResponseEntity<List<FeePaymentResponseDTO>> getPaymentsByLearnerId(
            @PathVariable Integer learnerId) {
        // Nota: tu servicio devuelve solo un FeePaymentResponseDTO, 
        // si quieres todos los pagos, se debería cambiar el servicio para devolver List<DTO>
        return ResponseEntity.ok(List.of(feePaymentService.findByLearnerId(learnerId)));
    }
}

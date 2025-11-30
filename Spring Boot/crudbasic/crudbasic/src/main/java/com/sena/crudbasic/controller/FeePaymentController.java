package com.sena.crudbasic.controller;

import com.sena.crudbasic.dto.FeePaymentDto;
import com.sena.crudbasic.service.IFeePaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fee-payment")
public class FeePaymentController {

    private final IFeePaymentService service;

    public FeePaymentController(IFeePaymentService service) {
        this.service = service;
    }

    @GetMapping
    public List<FeePaymentDto> getAll() {
        return service.getAll();
    }

    // CORREGIDO
    @GetMapping("/{id}")
    public FeePaymentDto getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public FeePaymentDto save(@RequestBody FeePaymentDto dto) {
        return service.save(dto);
    }

    @PutMapping("/update/{id}")
    public FeePaymentDto update(@PathVariable int id, @RequestBody FeePaymentDto dto) {
        return service.update(id, dto);
    }

    // CORREGIDO
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}

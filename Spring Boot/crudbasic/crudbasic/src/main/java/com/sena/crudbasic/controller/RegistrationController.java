package com.sena.crudbasic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private com.sena.crudbasic.repository.IRegistrationRepository repository;

    @GetMapping("/all")
    public List<com.sena.crudbasic.model.Registration> getAll() {
        return repository.findAll();
    }

    @PostMapping("/save")
    public com.sena.crudbasic.model.Registration save(@RequestBody com.sena.crudbasic.model.Registration p) {
        return repository.save(p);
    }

    @GetMapping("/{id}")
    public com.sena.crudbasic.model.Registration getById(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    // ✅ CORREGIDO
    @PutMapping("/update/{id}")
    public com.sena.crudbasic.model.Registration update(
            @PathVariable int id,
            @RequestBody com.sena.crudbasic.model.Registration data) {

        com.sena.crudbasic.model.Registration p = repository.findById(id).orElse(null);
        if (p == null) return null;

        p.setRegisteredOn(data.getRegisteredOn());
        p.setLearner(data.getLearner());
        p.setTrainingModule(data.getTrainingModule());

        return repository.save(p);
    }

    // ✅ CORREGIDO
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }
}

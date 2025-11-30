package com.sena.crudbasic.controller;

import com.sena.crudbasic.model.Module_Assignment;
import com.sena.crudbasic.repository.IModuleAssignmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("moduleAssignment")
public class ModuleAssignmentController {

    @Autowired
    private IModuleAssignmentRepository repository;

    @GetMapping("/all")
    public List<Module_Assignment> getAll() {
        return repository.findAll();
    }

    @PostMapping("/save")
    public Module_Assignment save(@RequestBody Module_Assignment m) {
        return repository.save(m);
    }

    // CORREGIDO
    @GetMapping("/{id}")
    public Module_Assignment getById(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    // CORREGIDO
    @PutMapping("/update/{id}")
    public Module_Assignment update(@PathVariable int id, @RequestBody Module_Assignment data) {
        Module_Assignment m = repository.findById(id).orElse(null);
        if (m == null) return null;

        m.setProgramType(data.getProgramType());
        m.setTrainingModule(data.getTrainingModule());

        return repository.save(m);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }
}

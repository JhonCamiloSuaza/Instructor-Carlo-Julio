package com.sena.crudbasic.controller;

import com.sena.crudbasic.model.ProgramType;
import com.sena.crudbasic.repository.IProgramTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("programType")
public class ProgramTypeController {

    @Autowired
    private IProgramTypeRepository repository;

    @GetMapping("all")
    public List<ProgramType> getAll() {
        return repository.findAll();
    }

    @PostMapping("save")
    public ProgramType save(@RequestBody ProgramType p) {
        return repository.save(p);
    }

    @GetMapping("/id")
    public ProgramType getById(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/update/{id}")
    public ProgramType update(@PathVariable int id, @RequestBody ProgramType data) {
        ProgramType p = repository.findById(id).orElse(null);
        if (p == null) return null;

        p.setTypeName(data.getTypeName());

        return repository.save(p);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }
}

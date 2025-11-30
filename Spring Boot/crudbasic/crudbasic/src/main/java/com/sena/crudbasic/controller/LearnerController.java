package com.sena.crudbasic.controller;

import com.sena.crudbasic.model.Learner;
import com.sena.crudbasic.repository.ILearnerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("learner")
public class LearnerController {

    @Autowired
    private ILearnerRepository repository;

    @GetMapping("/all")
    public List<Learner> getAll() {
        return repository.findAll();
    }

    @PostMapping("/save")
    public Learner save(@RequestBody Learner l) {
        return repository.save(l);
    }

    // CORREGIDO
    @GetMapping("/{id}")
    public Learner getById(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    // CORREGIDO
    @PutMapping("/update/{id}")
    public Learner update(@PathVariable int id, @RequestBody Learner data) {
        Learner l = repository.findById(id).orElse(null);
        if (l == null) return null;

        l.setLearnerName(data.getLearnerName());
        l.setYearsOld(data.getYearsOld());
        l.setSystemUser(data.getSystemUser());

        return repository.save(l);
    }

    // CORREGIDO
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }
}

package com.sena.crudbasic.controller;

import com.sena.crudbasic.model.Teacher;
import com.sena.crudbasic.repository.ITeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private ITeacherRepository repository;

    @GetMapping("/all")
    public List<Teacher> getAll() {
        return repository.findAll();
    }

    @PostMapping("/save")
    public Teacher save(@RequestBody Teacher t) {
        return repository.save(t);
    }

    // ✅ CORREGIDO
    @GetMapping("/{id}")
    public Teacher getById(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/update/{id}")
    public Teacher update(@PathVariable int id, @RequestBody Teacher data) {
        Teacher t = repository.findById(id).orElse(null);
        if (t == null) return null;

        t.setTeacherName(data.getTeacherName());

        return repository.save(t);
    }

    // ✅ CORREGIDO
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }
}

package com.sena.crudbasic.controller;

import com.sena.crudbasic.model.Teacher_Module;
import com.sena.crudbasic.repository.ITeacherModuleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacherModule")
public class TeacherModuleController {

    @Autowired
    private ITeacherModuleRepository repository;

    @GetMapping("/all")
    public List<Teacher_Module> getAll() {
        return repository.findAll();
    }

    @PostMapping("/save")
    public Teacher_Module save(@RequestBody Teacher_Module t) {
        return repository.save(t);
    }

    // ✅ CORREGIDO
    @GetMapping("/{id}")
    public Teacher_Module getById(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/update/{id}")
    public Teacher_Module update(@PathVariable int id, @RequestBody Teacher_Module data) {
        Teacher_Module t = repository.findById(id).orElse(null);
        if (t == null) return null;

        t.setTeacher(data.getTeacher());
        t.setTrainingModule(data.getTrainingModule());

        return repository.save(t);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }
}

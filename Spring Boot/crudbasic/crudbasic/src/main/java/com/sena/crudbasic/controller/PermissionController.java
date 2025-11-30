package com.sena.crudbasic.controller;

import com.sena.crudbasic.model.Permission;
import com.sena.crudbasic.repository.IPermissionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private IPermissionRepository repository;

    @GetMapping("/all")
    public List<Permission> getAll() {
        return repository.findAll();
    }

    @PostMapping("/save")
    public Permission save(@RequestBody Permission p) {
        return repository.save(p);
    }

    @GetMapping("/{id}")
    public Permission getById(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/update/{id}")
    public Permission update(@PathVariable int id, @RequestBody Permission data) {
        Permission p = repository.findById(id).orElse(null);
        if (p == null) return null;

        p.setPermissionName(data.getPermissionName());

        return repository.save(p);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }
}

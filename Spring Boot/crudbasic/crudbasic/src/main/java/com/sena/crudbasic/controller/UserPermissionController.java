package com.sena.crudbasic.controller;

import com.sena.crudbasic.model.User_Permission;
import com.sena.crudbasic.repository.IUserPermissionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("userPermission")
public class UserPermissionController {

    @Autowired
    private IUserPermissionRepository repository;

    @GetMapping("all")
    public List<User_Permission> getAll() {
        return repository.findAll();
    }

    @PostMapping("save")
    public User_Permission save(@RequestBody User_Permission u) {
        return repository.save(u);
    }

    @GetMapping("id")
    public User_Permission getById(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("update/{id}")
    public User_Permission update(@PathVariable int id, @RequestBody User_Permission data) {
        User_Permission u = repository.findById(id).orElse(null);
        if (u == null) return null;

        u.setSystemUser(data.getSystemUser());
        u.setPermission(data.getPermission());

        return repository.save(u);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }
}

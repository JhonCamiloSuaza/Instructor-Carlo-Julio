package com.sena.crudbasic.controller;

import com.sena.crudbasic.model.UserSystms;
import com.sena.crudbasic.repository.IUserSystmsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("userSystms")
public class UserSystmsController {

    @Autowired
    private IUserSystmsRepository repository;

    @GetMapping("all")
    public List<UserSystms> getAll() {
        return repository.findAll();
    }

    @PostMapping("save")
    public UserSystms save(@RequestBody UserSystms u) {
        return repository.save(u);
    }

    @GetMapping("id}")
    public UserSystms getById(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("update/{id}")
    public UserSystms update(@PathVariable int id, @RequestBody UserSystms data) {
        UserSystms u = repository.findById(id).orElse(null);
        if (u == null) return null;

        u.setLoginName(data.getLoginName());

        return repository.save(u);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }
}

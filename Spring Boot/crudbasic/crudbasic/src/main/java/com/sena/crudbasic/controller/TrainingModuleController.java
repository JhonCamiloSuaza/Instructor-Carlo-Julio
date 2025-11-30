package com.sena.crudbasic.controller;

import com.sena.crudbasic.model.TrainingModule;
import com.sena.crudbasic.repository.ITrainingModuleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainingModule")
public class TrainingModuleController {

    @Autowired
    private ITrainingModuleRepository repository;

    @GetMapping("/all")
    public List<TrainingModule> getAll() {
        return repository.findAll();
    }

    @PostMapping("/save")
    public TrainingModule save(@RequestBody TrainingModule t) {
        return repository.save(t);
    }

    @GetMapping("/{id}")
    public TrainingModule getById(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/update/{id}")
    public TrainingModule update(@PathVariable int id, @RequestBody TrainingModule data) {
        TrainingModule t = repository.findById(id).orElse(null);

        if (t == null) {
            return null;
        }

        // 👇 CAMPOS CORRECTOS
        t.setModuleName(data.getModuleCost());
        t.setModuleCost(data.getModuleCost());

        return repository.save(t);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }
}

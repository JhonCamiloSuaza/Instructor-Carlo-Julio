package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.model.Module_Assignment;
import com.sena.crudbasic.repository.IModuleAssignmentRepository;
import com.sena.crudbasic.service.IModuleAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleAssignmentServiceImpl implements IModuleAssignmentService {

    @Autowired
    private IModuleAssignmentRepository repository;

    @Override
    public List<Module_Assignment> getAll() {
        return repository.findAll();
    }

    @Override
    public Module_Assignment getById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Module_Assignment save(Module_Assignment moduleAssignment) {
        return repository.save(moduleAssignment);
    }

    @Override
    public Module_Assignment update(Module_Assignment moduleAssignment) {
        return repository.save(moduleAssignment);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}

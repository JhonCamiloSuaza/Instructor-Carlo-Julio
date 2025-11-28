package com.sena.crudbasic.service;

import java.util.List;
import com.sena.crudbasic.model.Module_Assignment;

public interface IModuleAssignmentService {
    List<Module_Assignment> getAll();
    Module_Assignment getById(int id);
    Module_Assignment save(Module_Assignment moduleAssignment);
    Module_Assignment update(Module_Assignment moduleAssignment);
    void delete(int id);
}

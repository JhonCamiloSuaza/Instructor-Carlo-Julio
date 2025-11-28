package com.sena.crudbasic.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "program_type")
public class ProgramType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_program_type")
    private int id;

    @Column(name = "type_name")
    private String typeName;

    @OneToMany(mappedBy = "programType")
    private List<Module_Assignment> modules;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Module_Assignment> getModules() {
        return modules;
    }

    public void setModules(List<Module_Assignment> modules) {
        this.modules = modules;
    }


    
}

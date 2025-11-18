package com.sena.crudbasic.Model;

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
}

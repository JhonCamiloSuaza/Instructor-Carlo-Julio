package com.sena.crudbasic.Model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "training_module")
public class TrainingModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_training_module")
    private int id;

    @Column(name = "module_title")
    private String moduleTitle;

    @Column(name = "module_cost")
    private BigDecimal moduleCost;

    @OneToMany(mappedBy = "trainingModule")
    private List<Module_Assignment> assignments;

    @OneToMany(mappedBy = "trainingModule")
    private List<Teacher_Module> teachers;

    @OneToMany(mappedBy = "trainingModule")
    private List<Registration> registrations;
}

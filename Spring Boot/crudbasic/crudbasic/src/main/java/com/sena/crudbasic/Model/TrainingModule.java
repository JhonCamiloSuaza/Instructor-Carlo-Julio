package com.sena.crudbasic.model;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public BigDecimal getModuleCost() {
        return moduleCost;
    }

    public void setModuleCost(BigDecimal moduleCost) {
        this.moduleCost = moduleCost;
    }

    public List<Module_Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Module_Assignment> assignments) {
        this.assignments = assignments;
    }

    public List<Teacher_Module> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher_Module> teachers) {
        this.teachers = teachers;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public void setModuleName(BigDecimal moduleCost) {
        throw new UnsupportedOperationException("Not supported yet.");
    }




    
}

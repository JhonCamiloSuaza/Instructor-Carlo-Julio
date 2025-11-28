package com.sena.crudbasic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "module_assignment")
public class Module_Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_module_assignment")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_program_type")
    private ProgramType programType;

    @ManyToOne
    @JoinColumn(name = "id_training_module")
    private TrainingModule trainingModule;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProgramType getProgramType() {
        return programType;
    }

    public void setProgramType(ProgramType programType) {
        this.programType = programType;
    }

    public TrainingModule getTrainingModule() {
        return trainingModule;
    }

    public void setTrainingModule(TrainingModule trainingModule) {
        this.trainingModule = trainingModule;
    }



    
}

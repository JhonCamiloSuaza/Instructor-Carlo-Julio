package com.sena.crudbasic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "teacher_module")
public class Teacher_Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_teacher_module")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_teacher")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "id_training_module")
    private TrainingModule trainingModule;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public TrainingModule getTrainingModule() {
        return trainingModule;
    }

    public void setTrainingModule(TrainingModule trainingModule) {
        this.trainingModule = trainingModule;
    }


    
}

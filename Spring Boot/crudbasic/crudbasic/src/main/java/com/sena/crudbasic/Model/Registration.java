package com.sena.crudbasic.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "registration")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registration")
    private int id;

    @Column(name = "registered_on")
    private LocalDate registeredOn;

    @ManyToOne
    @JoinColumn(name = "id_learner")
    private Learner learner;

    @ManyToOne
    @JoinColumn(name = "id_training_module")
    private TrainingModule trainingModule;

    @OneToMany(mappedBy = "registration")
    private List<FeePayment> payments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Learner getLearner() {
        return learner;
    }

    public void setLearner(Learner learner) {
        this.learner = learner;
    }

    public TrainingModule getTrainingModule() {
        return trainingModule;
    }

    public void setTrainingModule(TrainingModule trainingModule) {
        this.trainingModule = trainingModule;
    }

    public List<FeePayment> getPayments() {
        return payments;
    }

    public void setPayments(List<FeePayment> payments) {
        this.payments = payments;
    }

    public Object getTeacher() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTeacher'");
    }



    
}

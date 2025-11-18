package com.sena.crudbasic.Model;

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
}

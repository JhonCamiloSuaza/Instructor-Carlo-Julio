package com.sena.crudbasic.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "learner")
public class Learner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_learner")
    private int id;

    @Column(name = "learner_name")
    private String learnerName;

    @Column(name = "years_old")
    private int yearsOld;

    @OneToMany(mappedBy = "learner")
    private List<Registration> registrations;

    @OneToOne
    @JoinColumn(name = "id_system_user")
    private SystemUser systemUser;
}

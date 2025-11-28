package com.sena.crudbasic.model;

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
    private UserSystms systemUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLearnerName() {
        return learnerName;
    }

    public void setLearnerName(String learnerName) {
        this.learnerName = learnerName;
    }

    public int getYearsOld() {
        return yearsOld;
    }

    public void setYearsOld(int yearsOld) {
        this.yearsOld = yearsOld;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public UserSystms getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(UserSystms systemUser) {
        this.systemUser = systemUser;
    }



    
}

package com.sena.crudbasic.dto;

import java.time.LocalDate;

public class RegistrationDto {

    private int id;
    private LocalDate registeredOn;
    private int learnerId;
    private int trainingModuleId;

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

    public int getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(int learnerId) {
        this.learnerId = learnerId;
    }

    public int getTrainingModuleId() {
        return trainingModuleId;
    }

    public void setTrainingModuleId(int trainingModuleId) {
        this.trainingModuleId = trainingModuleId;
    }
}

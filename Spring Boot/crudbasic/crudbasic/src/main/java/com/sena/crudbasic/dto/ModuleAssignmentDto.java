package com.sena.crudbasic.dto;

public class ModuleAssignmentDto {

    private int id;
    private int programTypeId;
    private int trainingModuleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProgramTypeId() {
        return programTypeId;
    }

    public void setProgramTypeId(int programTypeId) {
        this.programTypeId = programTypeId;
    }

    public int getTrainingModuleId() {
        return trainingModuleId;
    }

    public void setTrainingModuleId(int trainingModuleId) {
        this.trainingModuleId = trainingModuleId;
    }
}

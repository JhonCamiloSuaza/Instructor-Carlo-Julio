package com.sena.crudbasic.dto;

import java.math.BigDecimal;

public class TrainingModuleDto {

    private int id;
    private String moduleTitle;
    private BigDecimal moduleCost;

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
}

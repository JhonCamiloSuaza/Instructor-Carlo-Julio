package com.sena.crudbasic.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "training_module")
public class TrainingModule extends BaseEntity {

    @Column(name = "module_title", nullable = false, length = 150)
    private String moduleTitle;

    @Column(name = "module_cost", nullable = false)
    private BigDecimal moduleCost;

    @OneToMany(mappedBy = "trainingModule")
    private List<ModuleAssignment> assignments;

    @OneToMany(mappedBy = "trainingModule")
    private List<TeacherModule> teachers;

    @OneToMany(mappedBy = "trainingModule")
    private List<Registration> registrations;
}

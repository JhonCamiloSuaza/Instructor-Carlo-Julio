package com.sena.crudbasic.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "registration")
public class Registration extends BaseEntity {

    @Column(name = "registered_on", nullable = false)
    private LocalDate registeredOn;

    @ManyToOne
    @JoinColumn(name = "id_learner", nullable = false)
    private Learner learner;

    @ManyToOne
    @JoinColumn(name = "id_training_module", nullable = false)
    private TrainingModule trainingModule;

    @OneToMany(mappedBy = "registration")
    private List<FeePayment> payments;

}

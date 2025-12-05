package com.sena.crudbasic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "teacher_module")
public class TeacherModule extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "id_teacher", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "id_training_module", nullable = false)
    private TrainingModule trainingModule;

}

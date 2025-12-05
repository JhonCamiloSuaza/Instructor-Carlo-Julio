package com.sena.crudbasic.model;

import java.time.Instant;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "module_assignment")
public class ModuleAssignment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "id_program_type", nullable = false)
    private ProgramType programType;

    @ManyToOne
    @JoinColumn(name = "id_training_module", nullable = false)
    private TrainingModule trainingModule;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
        if (active == null) active = true;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }
}

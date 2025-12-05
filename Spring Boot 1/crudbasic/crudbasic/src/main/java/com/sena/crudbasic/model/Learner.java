package com.sena.crudbasic.model;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "learner")
public class Learner extends BaseEntity {

    @Column(name = "learner_name", nullable = false)
    private String learnerName;

    @Column(name = "years_old", nullable = false)
    private int yearsOld;

    @OneToMany(mappedBy = "learner")
    private List<Registration> registrations;

    @OneToOne
    @JoinColumn(name = "id_system_user", nullable = false)
    private UserSystms systemUser;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    
    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}

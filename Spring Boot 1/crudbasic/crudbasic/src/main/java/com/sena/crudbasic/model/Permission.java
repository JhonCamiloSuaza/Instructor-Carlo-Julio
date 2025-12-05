package com.sena.crudbasic.model;

import java.time.Instant;
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
@Table(name = "permission")
public class Permission extends BaseEntity {

    @Column(name = "permission_name", nullable = false)
    private String permissionName;

    @OneToMany(mappedBy = "permission")
    private List<UserPermission> users;

    public Instant getCreatedAt() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Instant getUpdatedAt() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUpdatedAt'");
    }

}

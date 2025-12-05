package com.sena.crudbasic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_permission")
public class UserPermission extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "id_system_user", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UserSystms systemUser;

    @ManyToOne
    @JoinColumn(name = "id_permission", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Permission permission;
}

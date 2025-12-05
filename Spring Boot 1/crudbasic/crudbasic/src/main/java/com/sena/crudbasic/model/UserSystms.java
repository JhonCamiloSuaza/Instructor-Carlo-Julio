package com.sena.crudbasic.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "user_systms")
public class UserSystms extends BaseEntity {

    private String loginName;

    @OneToMany(mappedBy = "systemUser")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<UserPermission> permissions;

    @OneToOne(mappedBy = "systemUser")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Learner learner;
}

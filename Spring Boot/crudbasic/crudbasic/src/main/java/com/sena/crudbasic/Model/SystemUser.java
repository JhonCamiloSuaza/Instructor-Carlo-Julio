package com.sena.crudbasic.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "system_user")
public class SystemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_system_user")
    private int id;

    @Column(name = "login_name")
    private String loginName;

    @OneToMany(mappedBy = "systemUser")
    private List<User_Permission> permissions;

    @OneToOne(mappedBy = "systemUser")
    private Learner learner;
}

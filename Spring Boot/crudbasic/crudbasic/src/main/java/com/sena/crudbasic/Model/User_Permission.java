package com.sena.crudbasic.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "user_permission")
public class User_Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_permission")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_system_user")
    private SystemUser systemUser;

    @ManyToOne
    @JoinColumn(name = "id_permission")
    private Permission permission;
}

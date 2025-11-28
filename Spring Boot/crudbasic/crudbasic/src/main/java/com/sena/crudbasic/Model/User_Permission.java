package com.sena.crudbasic.model;

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
    private UserSystms systemUser;

    @ManyToOne
    @JoinColumn(name = "id_permission")
    private Permission permission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserSystms getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(UserSystms systemUser) {
        this.systemUser = systemUser;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    


    
}

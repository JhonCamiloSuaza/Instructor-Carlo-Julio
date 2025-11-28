package com.sena.crudbasic.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permission")
    private int id;

    @Column(name = "permission_name")
    private String permissionName;

    @OneToMany(mappedBy = "permission")
    private List<User_Permission> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public List<User_Permission> getUsers() {
        return users;
    }

    public void setUsers(List<User_Permission> users) {
        this.users = users;
    }



    
}

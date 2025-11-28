package com.sena.crudbasic.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "UserSystms")
public class UserSystms {

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public List<User_Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<User_Permission> permissions) {
        this.permissions = permissions;
    }

    public Learner getLearner() {
        return learner;
    }

    public void setLearner(Learner learner) {
        this.learner = learner;
    }


    
}

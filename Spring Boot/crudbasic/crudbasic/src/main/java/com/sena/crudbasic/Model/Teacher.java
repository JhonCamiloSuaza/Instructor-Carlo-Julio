package com.sena.crudbasic.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_teacher")
    private int id;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "expertise")
    private String expertise;

    @OneToMany(mappedBy = "teacher")
    private List<Teacher_Module> modules;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public List<Teacher_Module> getModules() {
        return modules;
    }

    public void setModules(List<Teacher_Module> modules) {
        this.modules = modules;
    }


    
    
}

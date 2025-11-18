package com.sena.crudbasic.Model;

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
}

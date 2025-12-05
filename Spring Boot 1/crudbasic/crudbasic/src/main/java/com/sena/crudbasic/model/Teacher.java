package com.sena.crudbasic.model;

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
@Table(name = "teacher")
public class Teacher extends BaseEntity {

    @Column(name = "teacher_name", nullable = false, length = 100)
    private String teacherName;

    @Column(name = "expertise", nullable = false, length = 100)
    private String expertise;

    @OneToMany(mappedBy = "teacher")
    private List<TeacherModule> modules;

}

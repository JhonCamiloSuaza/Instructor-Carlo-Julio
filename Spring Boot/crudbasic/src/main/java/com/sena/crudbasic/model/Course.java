package com.sena.crudbasic.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;

@Entity(name="course")
public class Course {
	
		//anotación bean para id de la entidad
		@Id
		//anotación para autoincremental
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		//indicar que el campo es una columna
		@Column(name="id_course")
		private int id;
		@Column(name="name", length = 50)
		private String name;
		
		@OneToMany(mappedBy = "course")
		private List<Student> students;
		

}

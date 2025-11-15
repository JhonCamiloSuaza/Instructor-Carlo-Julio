package com.sena.crudbasic.model;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name="student")

public class Student {
	//anotación bean para id de la entidad
	@Id
	//anotación para autoincremental
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//indicar que el campo es una columna
	@Column(name="id_student")
	private int id;
	
	@Column(name="name", length = 50)
	private String name;
	
	@ManyToOne
	@JoinColumn(name="id_course")//name="nombre llave primaria"
	private Course course;
}

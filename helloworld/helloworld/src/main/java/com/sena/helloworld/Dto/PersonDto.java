package com.sena.helloworld.Dto;

public class PersonDto {
    private String nombre;
    private String apellido;
    private int edad;

    // Constructor sin argumentos (necesario para deserialización JSON con Spring/Jackson)
    public PersonDto() {
    }

    public PersonDto(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

}

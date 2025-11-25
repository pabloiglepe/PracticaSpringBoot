package com.company.nominasspringboot.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Persona {

    @Column(name = "nombre")
    public String nombre;

    @Id
    @Column(name ="Dni")
    public String dni;

    @Column(name = "Sexo")
    public char sexo;


    /**
     *
     * @param nombre
     * @param dni
     * @param sexo
     */
    public Persona(String nombre, String dni, char sexo) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
    }

    /**
     *
     * @param nombre
     * @param sexo
     */
    public Persona(String nombre, char sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

//    Constructor sin argumentos requerido por JPA
    public Persona() {

    }

    /**
     *
     * @param dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     *
     * @return
     */
    public String Imprime() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }
}

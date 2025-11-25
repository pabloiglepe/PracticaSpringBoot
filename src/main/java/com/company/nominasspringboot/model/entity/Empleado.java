package com.company.nominasspringboot.model.entity;

import com.company.nominasspringboot.excepciones.DatosNoCorrectosExcepcion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "empleado")
public class Empleado extends Persona {

    @Column(name = "Categoria")
    private int categoria;

    @Column(name = "Anyos")
    public int anyos;


    public Empleado(char sexo, String dni, String nombre, int anyos, int categoria) {
        super(nombre, dni, sexo);
        this.anyos = anyos;
        this.categoria = categoria;
    }


    public Empleado(char sexo, String dni, String nombre) {
        super(nombre, dni, sexo);
        this.anyos = 0;
        this.categoria = 1;
    }


    public Empleado() {
        super();
    }


    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }


    public void incrAnyo() {
        this.anyos++;
    }


    public String getDni() {
        return dni;
    }

    public int getCategoria() {
        return categoria;
    }

    public int getAnyos() {
        return anyos;
    }

    public String getNombre() {
        return nombre;
    }

    public char getSexo() {
        return sexo;
    }


    public String Imprime() {
        return "Empleado{" +
                "categoria=" + categoria +
                ", anyos=" + anyos +
                ", sexo=" + sexo +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

package com.company.nominasspringboot.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "nomina")
public class Nomina {

    @Id
    @Column(name = "Dni")
    private String dni;

    @Column(name = "sueldo")
    private double sueldo;


//    RELACION CON LA ENTIDAD EMPLEADO
    @OneToOne(fetch = FetchType.LAZY)
//    INDICA QUE LA CLAVE PRIMARIA PROVIENE DE EMPLEADO
    @MapsId
    @JoinColumn(name = "Dni")
    private Empleado empleado;


    public Nomina(double sueldo, Empleado empleado) {
        // Al asignar el empleado, JPA usará su Dni como clave.
        this.sueldo = sueldo;
        this.empleado = empleado;
        this.dni = empleado.getDni(); // Inicializamos el DNI localmente
    }

    public Nomina() {

    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
        if (empleado != null) {
            this.dni = empleado.getDni();
        }
    }
}

package com.company.nominasspringboot.excepciones;

public class DatosNoCorrectosExcepcion extends RuntimeException{
    public DatosNoCorrectosExcepcion(String message) {
        super(message);
    }
}

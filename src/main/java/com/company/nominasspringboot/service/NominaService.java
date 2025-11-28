package com.company.nominasspringboot.service;

import com.company.nominasspringboot.model.entity.Empleado;
import org.springframework.stereotype.Service;

@Service
public class NominaService {

    private static final int SUELDO_BASE[] = {50000, 70000, 90000, 110000, 130000, 150000,
            170000, 190000, 210000, 230000};


    public double calcularSueldo(Empleado e) {
        int categoria = e.getCategoria();
        int longitud = SUELDO_BASE.length;

        if (categoria < 1 || categoria > longitud) {
            throw new IllegalArgumentException("La categoría " + categoria + " está fuera del rango [1-" + longitud + "]");
        }

        return (SUELDO_BASE[categoria - 1] + 5000 * e.getAnyos());
    }
}

package com.company.nominasspringboot.service;

import com.company.nominasspringboot.excepciones.RepositoryException;
import com.company.nominasspringboot.model.entity.Empleado;
import com.company.nominasspringboot.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;


    private static final int SUELDO_BASE[] = {50000, 70000, 90000, 110000, 130000, 150000,
            170000, 190000, 210000, 230000};


    public static double sueldo(Empleado e) {
        int categoria = e.getCategoria();
        return (SUELDO_BASE[categoria - 1] + 5000 * e.getAnyos());
    }


    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }


    public Optional<Empleado> mostrarSalarioPorDni(String dni) {
        return empleadoRepository.findSalarioByID(dni);
    }


    public List<Empleado> buscarEmpleadosParaModificar(String dni, String nombre, Integer categoria, Character sexo, Integer anyos) {
        return empleadoRepository.findByCriteriosBusqueda(dni, nombre, categoria, sexo, anyos);
    }
}

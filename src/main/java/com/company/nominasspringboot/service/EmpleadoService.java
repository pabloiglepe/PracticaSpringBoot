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

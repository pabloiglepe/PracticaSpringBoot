package com.company.nominasspringboot.controller;

import com.company.nominasspringboot.model.entity.Empleado;
import com.company.nominasspringboot.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/findAll")
    public String findAll(Model model) {
        try {
            List<Empleado> listaEmpleados = empleadoService.findAll();
            model.addAttribute("listaEmpleados", listaEmpleados);
            return "find-all-empleados";
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al recuperar la lista de todos los empleados", e);
        }
    }

    @GetMapping("/salario")
    public String mostrarSalarioPorDni(Model model, @RequestParam("dni") String dni) {
        try {
            Double salario = empleadoService.mostrarSalarioPorDni(dni);
            model.addAttribute("salario", salario);
            return "mostrar-salario";
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al recuperar la lista de todos los empleados", e);
        }
    }
}

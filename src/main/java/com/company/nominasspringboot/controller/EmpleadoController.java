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

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empresa")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/listado")
    public String findAll(Model model) {
        try {
            List<Empleado> listaEmpleados = empleadoService.findAll();
            model.addAttribute("listaEmpleados", listaEmpleados);
            return "find-all-empleados";
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al recuperar la lista de todos los empleados", e);
        }
    }

    @GetMapping("/formulario-salario")
    public String mostrarSalarioPorDni() {
        return "mostrar-salario";
    }

    @GetMapping("/salario")
    public String procesarSalarioPorDni(Model model, @RequestParam("dni") String dni) {
        try {
            Optional<Empleado> salario = empleadoService.mostrarSalarioPorDni(dni);
            model.addAttribute("salario", salario);
            return "mostrar-salario";
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al recuperar la lista de todos los empleados", e);
        }
    }

    @GetMapping("/formulario-busqueda")
    public String mostrarFormularioBusqueda() {
        return "buscar_empleado";
    }

    @GetMapping("/buscar")
    public String buscarPorCriterio(@RequestParam(name = "dni", required = false) String dni,
                                    @RequestParam(name = "nombre", required = false) String nombre,
                                    @RequestParam(name = "categoria", required = false) Integer categoria,
                                    @RequestParam(name = "sexo", required = false) Character sexo,
                                    @RequestParam(name = "anyos", required = false) Integer anyos,
                                    Model model) {
        try {
            List<Empleado> listaEmpleadosPorCriterio = empleadoService.buscarEmpleadosParaModificar(dni, nombre, categoria, sexo, anyos);
            model.addAttribute("listaEmpleadosPorCriterio", listaEmpleadosPorCriterio);
            return "buscar_empleado";
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al buscar segun el criterio", e);
        }
    }
}

package com.company.nominasspringboot.controller;

import com.company.nominasspringboot.model.entity.Empleado;
import com.company.nominasspringboot.service.EmpleadoService;
import com.company.nominasspringboot.service.NominaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empresa")
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final NominaService nominaService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService, NominaService nominaService) {
        this.empleadoService = empleadoService;
        this.nominaService = nominaService;
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

    @PostMapping("/salario")
    public String procesarSalarioPorDni(Model model, @RequestParam("dniEmpleado") String dni) {
        try {
            Optional<Empleado> empleadoOpt = empleadoService.mostrarSalarioPorDni(dni);

            if (empleadoOpt.isEmpty()) {
                model.addAttribute("errorBusqueda", "Error: Empleado con DNI " + dni + " no encontrado.");
                return "mostrar-salario";
            }

            double sueldo = nominaService.calcularSueldo(empleadoOpt.get());
            model.addAttribute("salario", sueldo);
            return "mostrar-salario";

        } catch (DataAccessException e) {
            model.addAttribute("errorBusqueda", "Error de acceso a datos.");
            return "mostrar-salario";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorBusqueda", e.getMessage());
            return "mostrar-salario";
        }
    }

    @GetMapping("/formulario-busqueda")
    public String mostrarFormularioBusqueda() {
        return "buscar_empleado";
    }

    @PostMapping("/buscar")
    public String buscarPorCriterio(@RequestParam(name = "dniBuscarEmpleado", required = false) String dni,
                                    @RequestParam(name = "nombreBuscarEmpleado", required = false) String nombre,
                                    @RequestParam(name = "categoriaBuscarEmpleado", required = false) Integer categoria,
                                    @RequestParam(name = "sexoBuscarEmpleado", required = false) Character sexo,
                                    @RequestParam(name = "anyosBuscarEmpleado", required = false) Integer anyos,
                                    Model model) {
        try {
            List<Empleado> listaEmpleadosPorCriterio = empleadoService.buscarEmpleadosParaModificar(dni, nombre, categoria, sexo, anyos);
            model.addAttribute("busquedaEmpleado", listaEmpleadosPorCriterio);
            return "buscar_empleado";
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al buscar segun el criterio", e);
        }
    }
}

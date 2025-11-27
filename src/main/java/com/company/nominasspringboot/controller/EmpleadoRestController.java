package com.company.nominasspringboot.controller;

import com.company.nominasspringboot.model.entity.Empleado;
import com.company.nominasspringboot.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empresa")
public class EmpleadoRestController {

    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoRestController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Empleado>> findAllEmpleados() {
        List<Empleado> empleados = empleadoService.findAll();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Empleado> findEmpleadoByDni(@PathVariable String dni) {
        Optional<Empleado> empleadoOpt = empleadoService.mostrarSalarioPorDni(dni);
        return empleadoOpt
                .map(empleado -> new ResponseEntity<>(empleado, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorCriterio(
            @RequestParam(name = "dni", required = false) String dni,
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "categoria", required = false) Integer categoria,
            @RequestParam(name = "sexo", required = false) Character sexo,
            @RequestParam(name = "anyos", required = false) Integer anyos) {

        List<Empleado> listaEmpleadosPorCriterio = empleadoService.buscarEmpleadosParaModificar(dni, nombre, categoria, sexo, anyos);
        return new ResponseEntity<>(listaEmpleadosPorCriterio, HttpStatus.OK);
    }

}

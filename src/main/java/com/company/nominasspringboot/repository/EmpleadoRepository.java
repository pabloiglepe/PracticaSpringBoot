package com.company.nominasspringboot.repository;

import com.company.nominasspringboot.model.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {

    @Query(value = "SELECT sueldo FROM nomina WHERE Dni = ?1", nativeQuery = true)
    Double findSalarioByID(String DNi);


    @Query("SELECT e FROM Empleado e WHERE e.dni = ?1 OR e.nombre = ?2 OR e.categoria = ?3 OR e.sexo = ?4 OR e.anyos = ?5")
    List<Empleado> findByCriteriosBusqueda(
            String dni,
            String nombre,
            Integer categoria,
            Character sexo,
            Integer anyos
    );
}

package com.example.delparque.repository;

import com.example.delparque.model.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrabajadoresRepository extends JpaRepository<Trabajador, String> {
    Trabajador findBynombreTrabajador(String nombreTrabajador);
}

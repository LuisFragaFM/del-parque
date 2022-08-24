package com.example.delparque.repository;

import com.example.delparque.model.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrabajadoresRepository extends JpaRepository<Trabajador, String> {
    Trabajador findTrabajadorBynombreTrabajador(String nombreTrabajador);
}

package com.example.delparque.service;

import com.example.delparque.dto.Trabajador;

import java.util.List;
import java.util.Optional;

public interface TrabajadoresService {
    List<Trabajador> findAll();

    Optional<Trabajador> findById(String id);

    Trabajador save(Trabajador trabajador);

    void delete(String id);
}

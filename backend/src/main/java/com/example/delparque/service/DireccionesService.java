package com.example.delparque.service;

import com.example.delparque.dto.Direccion;

import java.util.List;
import java.util.Optional;

public interface DireccionesService {
    List<Direccion> findAll();

    Optional<Direccion> findById(String id);

    Direccion save(Direccion direccion);
}

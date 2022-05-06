package com.example.delparque.service;

import com.example.delparque.dto.Paquete;

import java.util.List;
import java.util.Optional;

public interface PaquetesService {
    List<Paquete> findAll();

    Optional<Paquete> findById(String id);

    List<Paquete> findAllByEntregado(Boolean status);

    Paquete save(Paquete paquete);
}

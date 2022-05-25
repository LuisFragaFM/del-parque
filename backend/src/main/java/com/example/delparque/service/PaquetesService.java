package com.example.delparque.service;

import com.example.delparque.dto.Paquete;

import java.util.List;
import java.util.Optional;

public interface PaquetesService {
    Paquete save(Paquete paquete);

    List<Paquete> findAll();

    Paquete findById(String id);

    void delete(String id);
}

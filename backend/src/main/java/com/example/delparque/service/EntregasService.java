package com.example.delparque.service;

import com.example.delparque.dto.Entrega;

import java.util.List;
import java.util.Optional;

public interface EntregasService {
    List<Entrega> findAll();

    Optional<Entrega> findById(String id);

    Entrega save(Entrega entrega);
}

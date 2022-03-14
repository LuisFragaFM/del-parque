package com.example.delparque.service;

import com.example.delparque.dto.Paquete;

import java.util.List;

public interface PaquetesService {
    List<Paquete> findAll();

    List<Paquete> findAllByEntregado(Boolean status);
}

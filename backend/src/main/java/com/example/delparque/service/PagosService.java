package com.example.delparque.service;

import com.example.delparque.dto.Pago;

import java.util.List;
import java.util.Optional;

public interface PagosService {
    List<Pago> findAll();

    Optional<Pago> findById(String id);

    Pago save(Pago pago);
}

package com.example.delparque.service;

import com.example.delparque.dto.Condomino;

import java.util.List;
import java.util.Optional;

public interface CondominiosService {
    List<Condomino> findAll();

    Optional<Condomino> findById(String id);

    Condomino save(Condomino condominio);
}

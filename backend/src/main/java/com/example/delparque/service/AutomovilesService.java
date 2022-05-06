package com.example.delparque.service;

import com.example.delparque.dto.Automovil;

import java.util.List;
import java.util.Optional;

public interface AutomovilesService {
    List<Automovil> findAll();

    Optional<Automovil> findById(String id);

    Automovil save(Automovil automovil);

    void delete(String id);
}

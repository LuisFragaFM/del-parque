package com.example.delparque.service;

import com.example.delparque.dto.Condomino;

import java.util.List;
import java.util.Optional;

public interface CondominosService {
    List<Condomino> findAll();

    Condomino findById(String id);

    Condomino findByNumeroTelefono(String numeroTelefono);

    Condomino findByNombre(String nombre);

    Condomino save(Condomino condomino);

    void delete(String id);
}

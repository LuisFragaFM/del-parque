package com.example.delparque.service;

import com.example.delparque.dto.Condomino;
import org.springframework.data.domain.Page;


public interface CondominosService {
    Page<Condomino> findAll(Integer page);

    Condomino findById(String id);

    Condomino findByNumeroTelefono(String numeroTelefono);

    Condomino findByNombre(String nombre);

    Condomino save(Condomino condomino);

    void delete(String id);
}

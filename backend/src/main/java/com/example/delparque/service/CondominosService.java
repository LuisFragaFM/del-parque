package com.example.delparque.service;

import com.example.delparque.dto.Condomino;
import org.springframework.data.domain.Page;

import java.util.List;


public interface CondominosService {
    Page<Condomino> findAll(Integer page);

    Condomino findById(String id);

    Condomino findByNumeroTelefono(String numeroTelefono);

    List<Condomino> findByName(String name);

    Condomino save(Condomino condomino);

    void delete(String id);
}

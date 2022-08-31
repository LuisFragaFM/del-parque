package com.example.delparque.service;

import com.example.delparque.dto.Trabajador;
import org.springframework.data.domain.Page;

public interface TrabajadoresService {
    Page<Trabajador> findAll(Integer page);

    Trabajador findById(String id);

    Trabajador findByName(String name);

    Trabajador save(Trabajador trabajador);

    void delete(String id);
}

package com.example.delparque.service;

import com.example.delparque.dto.Trabajador;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TrabajadoresService {
    Page<Trabajador> findAll(Integer page);

    Trabajador findById(String id);

    List<Trabajador> findByName(String name);

    Trabajador save(Trabajador trabajador);

    void delete(String id);
}

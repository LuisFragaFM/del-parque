package com.example.delparque.service;

import com.example.delparque.dto.Visitante;

import java.util.List;
import java.util.Optional;

public interface VisitantesService {
    List<Visitante> findAll();

    Visitante findById(String id);

    Visitante save(Visitante visitante);

    void delete(String id);
}

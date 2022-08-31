package com.example.delparque.service;

import com.example.delparque.dto.Visitante;
import org.springframework.data.domain.Page;

public interface VisitantesService {
    Page<Visitante> findAll(Integer page);

    Visitante findById(String id);

    Visitante save(Visitante visitante);

    void delete(String id);
}

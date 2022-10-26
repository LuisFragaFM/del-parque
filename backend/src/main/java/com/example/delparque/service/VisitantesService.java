package com.example.delparque.service;

import com.example.delparque.dto.Visitante;
import org.springframework.data.domain.Page;

public interface VisitantesService {
    Page<Visitante> findAllByAuthorized(Integer page, String userId);

    Page<Visitante> findAllByAuthorized(Integer page);

    Page<Visitante> findAllByGoneOut(Integer page);

    Visitante findById(String id);

    Visitante save(Visitante visitante);

    void delete(String id);
}

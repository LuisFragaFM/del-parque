package com.example.delparque.service;

import com.example.delparque.dto.Visitante;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VisitantesService {

    List<Visitante> findAll();

    Page<Visitante> findAllByAuthorized(Integer page, String userId);

    Page<Visitante> findAllByAuthorized(Integer page);

    Page<Visitante> findAllByGoneOut(Integer page);

    Visitante findById(String id);

    Visitante save(Visitante visitante);

    void delete(String id);
}

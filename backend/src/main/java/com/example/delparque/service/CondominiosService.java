package com.example.delparque.service;

import com.example.delparque.dto.Condominio;

import java.util.List;
import java.util.Optional;

public interface CondominiosService {
    List<Condominio> findAll();

    Optional<Condominio> findById(String id);

    Condominio save(Condominio condominio);
}

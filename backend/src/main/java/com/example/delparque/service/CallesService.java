package com.example.delparque.service;

import com.example.delparque.dto.Calle;

import java.util.List;
import java.util.Optional;

public interface CallesService {
    List<Calle> findAll();

    Optional<Calle> findById(String id);
}

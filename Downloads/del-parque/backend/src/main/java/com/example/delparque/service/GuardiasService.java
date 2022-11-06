package com.example.delparque.service;

import com.example.delparque.dto.Guardia;

public interface GuardiasService {
    Guardia findById(String id);

    Guardia save(Guardia guardia);

    void delete(String id);
}

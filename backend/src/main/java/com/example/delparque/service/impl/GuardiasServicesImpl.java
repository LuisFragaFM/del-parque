package com.example.delparque.service.impl;

import com.example.delparque.dto.Guardia;
import com.example.delparque.repository.GuardiasRepository;
import com.example.delparque.service.GuardiasService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GuardiasServicesImpl implements GuardiasService {

    private final GuardiasRepository guardiasRepository;

    GuardiasServicesImpl(GuardiasRepository guardiasRepository) {
        this.guardiasRepository = guardiasRepository;
    }

    @Override
    public Guardia findById(String id) {
        return null;
    }

    @Override
    public Guardia save(Guardia guardia) {
        if (guardia.getId() == null) {
            guardia.setId(UUID.randomUUID().toString());
        }
        return null;
    }

    @Override
    public void delete(String id) {

    }
}

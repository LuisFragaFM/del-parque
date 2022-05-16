package com.example.delparque.service.impl;

import com.example.delparque.dto.Paquete;
import com.example.delparque.repository.PaquetesRepository;
import com.example.delparque.service.PaquetesService;
import com.example.delparque.service.mappers.PaqueteMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaquetesServiceImpl implements PaquetesService {

    private final PaquetesRepository paquetesRepository;

    PaquetesServiceImpl(PaquetesRepository paquetesRepository) {
        this.paquetesRepository = paquetesRepository;
    }

    @Override
    public Paquete save(Paquete paquete) {
        if (paquete.getId() == null) {
            paquete.setId(UUID.randomUUID().toString());
        }

        return PaqueteMapper.entityToDto(paquetesRepository.save(PaqueteMapper.dtoToEntity(paquete)));
    }

    @Override
    public List<Paquete> findAll() {
        return paquetesRepository.findAll().stream()
                .map(PaqueteMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Paquete> findById(String id) {
        return Optional.ofNullable(PaqueteMapper.entityToDto(
                Objects.requireNonNull(paquetesRepository.findById(id).orElse(null))));
    }

    @Override
    public void delete(String id) {
        paquetesRepository.deleteById(id);
    }
}

package com.example.delparque.service.impl;

import com.example.delparque.dto.Entrega;
import com.example.delparque.repository.EntregasRepository;
import com.example.delparque.repository.PaquetesRepository;
import com.example.delparque.service.EntregasService;
import com.example.delparque.service.mappers.EntregasMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EntregasServiceImpl implements EntregasService {

    private final EntregasRepository entregasRepository;
    private final PaquetesRepository paquetesRepository;

    public EntregasServiceImpl(EntregasRepository entregasRepository,
                               PaquetesRepository paquetesRepository) {
        this.entregasRepository = entregasRepository;
        this.paquetesRepository = paquetesRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entrega> findAll() {
        return entregasRepository.findAll().stream()
                .map(EntregasMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Entrega> findById(String id) {
        return Optional.ofNullable(EntregasMapper.entityToDto(entregasRepository.findById(id)
                .orElse(new com.example.delparque.model.Entrega())));
    }

    @Override
    public Entrega save(Entrega entrega) {
        paquetesRepository.findById(entrega.getIdPaquete()).orElseThrow();

        if (entrega.getId() == null) {
            entrega.setId(UUID.randomUUID().toString());
        }

        return EntregasMapper.entityToDto(entregasRepository.save(EntregasMapper.dtoToEntity(entrega)));
    }
}

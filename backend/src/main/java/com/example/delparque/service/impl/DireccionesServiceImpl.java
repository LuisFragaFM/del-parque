package com.example.delparque.service.impl;

import com.example.delparque.dto.Direccion;
import com.example.delparque.repository.CallesRepository;
import com.example.delparque.repository.DireccionesRepository;
import com.example.delparque.service.DireccionesService;
import com.example.delparque.service.mappers.DireccionesMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DireccionesServiceImpl implements DireccionesService {

    private final DireccionesRepository direccionesRepository;
    private final CallesRepository callesRepository;

    public DireccionesServiceImpl(DireccionesRepository direccionesRepository,
                                  CallesRepository callesRepository) {
        this.direccionesRepository = direccionesRepository;
        this.callesRepository = callesRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Direccion> findAll() {
        return direccionesRepository.findAll().stream()
                .map(DireccionesMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Direccion> findById(String id) {
        return Optional.ofNullable(DireccionesMapper.entityToDto(direccionesRepository.findById(id)
                .orElse(new com.example.delparque.model.Direccion())));
    }

    @Override
    public Direccion save(Direccion direccion) {
        callesRepository.findById(direccion.getIdCalle()).orElseThrow();

        if (direccion.getId() == null) {
            direccion.setId(UUID.randomUUID().toString());
        }

        return DireccionesMapper.entityToDto(direccionesRepository.save(DireccionesMapper.dtoToEntity(direccion)));
    }
}

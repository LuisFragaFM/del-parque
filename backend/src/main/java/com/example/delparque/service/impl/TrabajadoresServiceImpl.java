package com.example.delparque.service.impl;

import com.example.delparque.dto.Trabajador;
import com.example.delparque.repository.TrabajadoresRepository;
import com.example.delparque.service.TrabajadoresService;
import com.example.delparque.service.mappers.TrabajadorMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TrabajadoresServiceImpl implements TrabajadoresService {

    private final TrabajadoresRepository trabajadoresRepository;

    TrabajadoresServiceImpl(TrabajadoresRepository trabajadoresRepository) {
        this.trabajadoresRepository = trabajadoresRepository;
    }

    @Override
    public List<Trabajador> findAll() {
        return trabajadoresRepository.findAll().stream()
                .map(TrabajadorMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Trabajador> findById(String id) {
        return Optional.ofNullable(TrabajadorMapper.entityToDto(
                Objects.requireNonNull(trabajadoresRepository.findById(id).orElse(null))));
    }

    @Override
    public Trabajador save(Trabajador trabajador) {
        if (trabajador.getId() == null) {
            trabajador.setId(UUID.randomUUID().toString());
        }
        return TrabajadorMapper.entityToDto(trabajadoresRepository.save(TrabajadorMapper.dtoToEntity(trabajador)));
    }

    @Override
    public void delete(String id) {
        trabajadoresRepository.deleteById(id);
    }
}

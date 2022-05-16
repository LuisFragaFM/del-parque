package com.example.delparque.service.impl;

import com.example.delparque.dto.Visitante;
import com.example.delparque.repository.VisitantesRepository;
import com.example.delparque.service.VisitantesService;
import com.example.delparque.service.mappers.VisitanteMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VisitantesServiceImpl implements VisitantesService {

    private final VisitantesRepository visitantesRepository;

    VisitantesServiceImpl(VisitantesRepository visitantesRepository) {
        this.visitantesRepository = visitantesRepository;
    }

    @Override
    public Optional<Visitante> findById(String id) {
        return Optional.ofNullable(VisitanteMapper.entityToDto(
                Objects.requireNonNull(visitantesRepository.findById(id).orElse(null))));
    }

    @Override
    public List<Visitante> findAll() {
        return visitantesRepository.findAll().stream()
                .map(VisitanteMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Visitante save(Visitante visitante) {
        if (visitante.getId() == null) {
            visitante.setId(UUID.randomUUID().toString());
        }

        return VisitanteMapper.entityToDto(visitantesRepository.save(VisitanteMapper.dtoToEntity(visitante)));
    }

    @Override
    public void delete(String id) {
        visitantesRepository.deleteById(id);
    }
}

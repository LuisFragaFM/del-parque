package com.example.delparque.service.impl;

import com.example.delparque.dto.Visitante;
import com.example.delparque.repository.VisitantesRepository;
import com.example.delparque.service.VisitantesService;
import com.example.delparque.service.mappers.VisitantesMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VisitantesServiceImpl implements VisitantesService {

    private final VisitantesRepository visitantesRepository;

    public VisitantesServiceImpl(VisitantesRepository visitantesRepository) {
        this.visitantesRepository = visitantesRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Visitante> findAll() {
        return visitantesRepository.findAll().stream()
                .map(VisitantesMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Visitante> findById(String id) {
        return Optional.ofNullable(VisitantesMapper.entityToDto(visitantesRepository.findById(id)
                .orElse(new com.example.delparque.model.Visitante())));
    }

    @Override
    public Visitante save(Visitante visitante) {
        if (visitante.getId() == null) {
            visitante.setId(UUID.randomUUID().toString());
        }

        return VisitantesMapper.entityToDto(visitantesRepository.save(VisitantesMapper.dtoToEntity(visitante)));
    }

}

package com.example.delparque.service.impl;

import com.example.delparque.dto.Visitante;
import com.example.delparque.repository.VisitantesRepository;
import com.example.delparque.service.VisitantesService;
import com.example.delparque.service.mappers.VisitanteMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VisitantesServiceImpl implements VisitantesService {

    private final VisitantesRepository visitantesRepository;

    VisitantesServiceImpl(VisitantesRepository visitantesRepository) {
        this.visitantesRepository = visitantesRepository;

    }

    @Override
    public Visitante findById(String id) {
        return visitantesRepository.findById(id)
                .map(VisitanteMapper::entityToDto)
                .orElse(null);
    }

    @Override
    public Page<Visitante> findAllByAuthorized(Integer page, String userId) {
        Pageable pageable = PageRequest.of(page, 10);

        return new PageImpl<>(
                visitantesRepository.findAllByAuthorizedIsAndUserId(false, userId).stream()
                        .map(VisitanteMapper::entityToDto)
                        .collect(Collectors.toList()),
                pageable, pageable.getPageSize()
        );
    }

    @Override
    public Page<Visitante> findAllByAuthorizedAndGoneOut(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);

        return new PageImpl<>(
                visitantesRepository.findAllByAuthorizedIsAndGoneOutIs(true, false).stream()
                        .map(VisitanteMapper::entityToDto)
                        .collect(Collectors.toList()),
                pageable, pageable.getPageSize()
        );
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

    @Override
    public List<Visitante> findByName(String name) {
        return visitantesRepository.findByName(name).stream()
                .map(VisitanteMapper::entityToDto)
                .collect(Collectors.toList());
    }
}

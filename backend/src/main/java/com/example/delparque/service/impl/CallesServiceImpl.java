package com.example.delparque.service.impl;

import com.example.delparque.dto.Calle;
import com.example.delparque.repository.CallesRepository;
import com.example.delparque.service.CallesService;
import com.example.delparque.service.mappers.CallesMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CallesServiceImpl implements CallesService {

    private final CallesRepository callesRepository;

    public CallesServiceImpl(CallesRepository callesRepository) {
        this.callesRepository = callesRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Calle> findAll() {
        return callesRepository.findAll().stream()
                .map(CallesMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Calle> findById(String id) {
        return Optional.ofNullable(CallesMapper.entityToDto(callesRepository.findById(id)
                .orElse(new com.example.delparque.model.Calle())));
    }
}

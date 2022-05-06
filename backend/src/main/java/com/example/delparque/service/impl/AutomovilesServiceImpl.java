package com.example.delparque.service.impl;

import com.example.delparque.dto.Automovil;
import com.example.delparque.repository.AutomovilesRepository;
import com.example.delparque.service.AutomovilesService;
import com.example.delparque.service.mappers.AutomovilesMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AutomovilesServiceImpl implements AutomovilesService {

    private final AutomovilesRepository automovilesRepository;

    public AutomovilesServiceImpl(AutomovilesRepository automovilesRepository) {
        this.automovilesRepository = automovilesRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Automovil> findAll() {
        return automovilesRepository.findAll().stream()
                .map(AutomovilesMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Automovil> findById(String id) {
        return Optional.ofNullable(AutomovilesMapper.entityToDto(automovilesRepository.findById(id)
                .orElse(new com.example.delparque.model.Automovil())));
    }

    @Override
    public Automovil save(Automovil automovil) {
        if (automovil.getId() == null) {
            automovil.setId(UUID.randomUUID().toString());
        }

        return AutomovilesMapper.entityToDto(automovilesRepository.save(AutomovilesMapper.dtoToEntity(automovil)));
    }

    @Override
    public void delete(String id) {
        automovilesRepository.deleteById(id);
    }
}

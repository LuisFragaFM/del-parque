package com.example.delparque.service.impl;

import com.example.delparque.dto.Paquete;
import com.example.delparque.repository.CondominosRepository;
import com.example.delparque.repository.PaquetesRepository;
import com.example.delparque.service.PaquetesService;
import com.example.delparque.service.mappers.PaquetesMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaquetesServiceImpl implements PaquetesService {

    private final PaquetesRepository paquetesRepository;
    private final CondominosRepository condominosRepository;

    public PaquetesServiceImpl(PaquetesRepository paquetesRepository, CondominosRepository condominosRepository) {
        this.paquetesRepository = paquetesRepository;
        this.condominosRepository = condominosRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paquete> findAll() {
        return paquetesRepository.findAll().stream()
                .map(PaquetesMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Paquete> findById(String id) {
        return Optional.ofNullable(PaquetesMapper.entityToDto(paquetesRepository.findById(id)
                .orElse(new com.example.delparque.model.Paquete())));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paquete> findAllByEntregado(Boolean status) {
        return paquetesRepository.findAllByEntregado(status).stream()
                .map(PaquetesMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Paquete save(Paquete paquete) {
        condominosRepository.findById(paquete.getIdCondominio()).orElseThrow();

        if (paquete.getId() == null) {
            paquete.setId(UUID.randomUUID().toString());
        }

        return PaquetesMapper.entityToDto(paquetesRepository.save(PaquetesMapper.dtoToEntity(paquete)));
    }
}

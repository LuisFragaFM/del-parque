package com.example.delparque.service.impl;

import com.example.delparque.dto.Condominio;
import com.example.delparque.repository.CondominiosRepository;
import com.example.delparque.repository.DireccionesRepository;
import com.example.delparque.repository.TrabajadoresRepository;
import com.example.delparque.repository.UserRepository;
import com.example.delparque.service.CondominiosService;
import com.example.delparque.service.mappers.CondominiosMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CondominiosServiceImpl implements CondominiosService {

    private final CondominiosRepository condominiosRepository;
    private final DireccionesRepository direccionesRepository;
    private final TrabajadoresRepository trabajadoresRepository;
    private final UserRepository usuariosRepository;

    public CondominiosServiceImpl(CondominiosRepository condominiosRepository,
                                  DireccionesRepository direccionesRepository,
                                  TrabajadoresRepository trabajadoresRepository,
                                  UserRepository usuariosRepository) {
        this.condominiosRepository = condominiosRepository;
        this.direccionesRepository = direccionesRepository;
        this.trabajadoresRepository = trabajadoresRepository;
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Condominio> findAll() {
        return condominiosRepository.findAll().stream()
                .map(CondominiosMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Condominio> findById(String id) {
        return Optional.ofNullable(CondominiosMapper.entityToDto(condominiosRepository.findById(id)
                .orElse(new com.example.delparque.model.Condominio())));
    }

    @Override
    public Condominio save(Condominio condominio) {
        direccionesRepository.findById(condominio.getIdDireccion()).orElseThrow();

        trabajadoresRepository.findById(condominio.getIdTrabajador()).orElseThrow();

        usuariosRepository.findById(condominio.getIdUsuario()).orElseThrow();

        if (condominio.getId() == null) {
            condominio.setId(UUID.randomUUID().toString());
        }

        return CondominiosMapper.entityToDto(condominiosRepository.save(CondominiosMapper.dtoToEntity(condominio)));
    }
}

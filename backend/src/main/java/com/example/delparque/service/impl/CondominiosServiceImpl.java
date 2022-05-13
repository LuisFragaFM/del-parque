package com.example.delparque.service.impl;

import com.example.delparque.dto.Condomino;
import com.example.delparque.repository.CondominosRepository;
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

    private final CondominosRepository condominosRepository;
    private final DireccionesRepository direccionesRepository;
    private final TrabajadoresRepository trabajadoresRepository;
    private final UserRepository usuariosRepository;

    public CondominiosServiceImpl(CondominosRepository condominosRepository,
                                  DireccionesRepository direccionesRepository,
                                  TrabajadoresRepository trabajadoresRepository,
                                  UserRepository usuariosRepository) {
        this.condominosRepository = condominosRepository;
        this.direccionesRepository = direccionesRepository;
        this.trabajadoresRepository = trabajadoresRepository;
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Condomino> findAll() {
        return condominosRepository.findAll().stream()
                .map(CondominiosMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Condomino> findById(String id) {
        return Optional.ofNullable(CondominiosMapper.entityToDto(condominosRepository.findById(id)
                .orElse(new com.example.delparque.model.Condomino())));
    }

    @Override
    public Condomino save(Condomino condominio) {
        direccionesRepository.findById(condominio.getIdDireccion()).orElseThrow();

        trabajadoresRepository.findById(condominio.getIdTrabajador()).orElseThrow();

        usuariosRepository.findById(condominio.getIdUsuario()).orElseThrow();

        if (condominio.getId() == null) {
            condominio.setId(UUID.randomUUID().toString());
        }

        return CondominiosMapper.entityToDto(condominosRepository.save(CondominiosMapper.dtoToEntity(condominio)));
    }
}

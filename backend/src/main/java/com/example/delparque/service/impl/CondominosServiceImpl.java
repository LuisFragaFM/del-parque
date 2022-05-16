package com.example.delparque.service.impl;

import com.example.delparque.dto.Condomino;
import com.example.delparque.repository.CondominosRepository;
import com.example.delparque.service.CondominosService;
import com.example.delparque.service.mappers.CondominoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CondominosServiceImpl implements CondominosService {

    private final CondominosRepository condominosRepository;

    CondominosServiceImpl(CondominosRepository condominosRepository) {
        this.condominosRepository = condominosRepository;
    }

    @Override
    public List<Condomino> findAll() {
        return condominosRepository.findAll().stream()
                .map(CondominoMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Condomino save(Condomino condomino) {
        if (condomino.getId() == null) {
            condomino.setId(UUID.randomUUID().toString());
        }

        return CondominoMapper.entityToDto(condominosRepository.save(CondominoMapper.dtoToEntity(condomino)));
    }

    @Override
    public Optional<Condomino> findById(String id) {
        return Optional.ofNullable(CondominoMapper.entityToDto(
                Objects.requireNonNull(condominosRepository.findById(id).orElse(null))));
    }

    @Override
    public Optional<Condomino> findByNumeroTelefono(String numeroTelefono) {
        return Optional.ofNullable(CondominoMapper.entityToDto(
                Objects.requireNonNull(condominosRepository.findByNumeroTelefono(numeroTelefono).orElse(null))));
    }

    @Override
    public Optional<Condomino> findByNombre(String nombre) {
        return Optional.ofNullable(CondominoMapper.entityToDto(
                Objects.requireNonNull(condominosRepository.findByNombre(nombre).orElse(null))));
    }

    @Override
    public void delete(String id) {
        condominosRepository.deleteById(id);
    }
}

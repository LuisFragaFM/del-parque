package com.example.delparque.service.impl;

import com.example.delparque.dto.Condomino;
import com.example.delparque.exception.BadRequestDataException;
import com.example.delparque.repository.CondominosRepository;
import com.example.delparque.service.CondominosService;
import com.example.delparque.service.mappers.CondominoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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

        if (condomino.getNumeroEmergencia() == null) {
            throw new BadRequestDataException("numero de emergencia requerido", "EMERGENCY_NUMBER_ERROR");
        }

        if (condomino.getDireccion() == null) {
            throw new BadRequestDataException("direccion requerida", "DIRECTION_ERROR");
        }

        if (condomino.getNumeroTelefono() == null) {
            throw new BadRequestDataException("telefono requerido", "NUMBER_ERROR");
        }

        if (condomino.getNombre() == null) {
            throw new BadRequestDataException("nombre de casa requerido", "HOUSE_NAME_ERROR");
        }

        if (condomino.getCorreoElectronico() == null) {
            throw new BadRequestDataException("email requerido", "EMAIL_ERROR");
        }

        return CondominoMapper.entityToDto(condominosRepository.save(CondominoMapper.dtoToEntity(condomino)));
    }

    @Override
    public Condomino findById(String id) {
        return condominosRepository.findById(id).map(CondominoMapper::entityToDto).orElse(null);
    }

    @Override
    public Condomino findByNumeroTelefono(String numeroTelefono) {
        return condominosRepository.findByNumeroTelefono(numeroTelefono).map(CondominoMapper::entityToDto).orElse(null);
    }

    @Override
    public Condomino findByNombre(String nombre) {
        return condominosRepository.findByNombre(nombre).map(CondominoMapper::entityToDto).orElse(null);
    }

    @Override
    public void delete(String id) {
        condominosRepository.deleteById(id);
    }
}

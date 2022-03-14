package com.example.delparque.service.impl;

import com.example.delparque.dto.Paquete;
import com.example.delparque.model.Condominio;
import com.example.delparque.repository.CondominiosRepository;
import com.example.delparque.repository.PaquetesRepository;
import com.example.delparque.service.PaquetesService;
import com.example.delparque.service.mappers.CondominiosMapper;
import com.example.delparque.service.mappers.PaquetesMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaquetesServiceImpl implements PaquetesService {

    private final PaquetesRepository paquetesRepository;
    private final CondominiosRepository condominiosRepository;

    public PaquetesServiceImpl(PaquetesRepository paquetesRepository, CondominiosRepository condominiosRepository) {
        this.paquetesRepository = paquetesRepository;
        this.condominiosRepository = condominiosRepository;
    }

    @Override
    public List<Paquete> findAll() {
        return paquetesRepository.findAll().stream()
                .map(p -> {
                    Condominio condominio = condominiosRepository.findById(p.getIdCondominio()).orElseThrow();

                    Paquete paquete = PaquetesMapper.entityToDto(p);

                    paquete.setCondominio(CondominiosMapper.entityToDto(condominio));

                    return paquete;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Paquete> findAllByEntregado(Boolean status) {
        return paquetesRepository.findAllByEntregado(status).stream()
                .map(p -> {
                    Condominio condominio = condominiosRepository.findById(p.getIdCondominio()).orElseThrow();

                    Paquete paquete = PaquetesMapper.entityToDto(p);

                    paquete.setCondominio(CondominiosMapper.entityToDto(condominio));

                    return paquete;
                })
                .collect(Collectors.toList());
    }
}

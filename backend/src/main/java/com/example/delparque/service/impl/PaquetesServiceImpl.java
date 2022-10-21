package com.example.delparque.service.impl;

import com.example.delparque.dto.CondominoInfo;
import com.example.delparque.dto.Paquete;
import com.example.delparque.model.Condomino;
import com.example.delparque.repository.CondominosRepository;
import com.example.delparque.repository.PaquetesRepository;
import com.example.delparque.service.PaquetesService;
import com.example.delparque.service.mappers.PaqueteMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaquetesServiceImpl implements PaquetesService {

    private final PaquetesRepository paquetesRepository;
    private final CondominosRepository condominosRepository;

    PaquetesServiceImpl(PaquetesRepository paquetesRepository, CondominosRepository condominosRepository) {
        this.paquetesRepository = paquetesRepository;
        this.condominosRepository = condominosRepository;
    }

    @Override
    public Paquete save(Paquete paquete) {
        if (paquete.getId() == null) {
            paquete.setId(UUID.randomUUID().toString());
        }

        return PaqueteMapper.entityToDto(paquetesRepository.save(PaqueteMapper.dtoToEntity(paquete)));
    }

    @Override
    public Page<Paquete> findAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);

        return new PageImpl<>(
                paquetesRepository.findAll().stream()
                        .map(this::addExtraInfo)
                        .collect(Collectors.toList()),
                pageable, pageable.getPageSize()
        );
    }

    @Override
    public Paquete findById(String id) {
        return paquetesRepository.findById(id).map(this::addExtraInfo).orElse(null);
    }

    @Override
    public void delete(String id) {
        paquetesRepository.deleteById(id);
    }

    private Paquete addExtraInfo(com.example.delparque.model.Paquete p) {
        Paquete paquete = PaqueteMapper.entityToDto(p);
        Condomino condomino = condominosRepository.findById(paquete.getCondomino().getUserId()).orElseThrow();

        CondominoInfo condominoInfo = CondominoInfo.builder()
                .userId(p.getCondominoId())
                .houseStreet(condomino.getStreet())
                .houseNumber(condomino.getHouseNumber())
                .build();

        paquete.setCondomino(condominoInfo);
        return paquete;
    }
}

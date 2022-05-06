package com.example.delparque.service.impl;

import com.example.delparque.dto.Pago;
import com.example.delparque.repository.CondominiosRepository;
import com.example.delparque.repository.PagosRepository;
import com.example.delparque.service.PagosService;
import com.example.delparque.service.mappers.PagosMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PagosServiceImpl implements PagosService {

    private final PagosRepository pagosRepository;
    private final CondominiosRepository condominiosRepository;

    public PagosServiceImpl(PagosRepository pagosRepository,
                            CondominiosRepository condominiosRepository) {
        this.pagosRepository = pagosRepository;
        this.condominiosRepository = condominiosRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pago> findAll() {
        return pagosRepository.findAll().stream()
                .map(PagosMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pago> findById(String id) {
        return Optional.ofNullable(PagosMapper.entityToDto(pagosRepository.findById(id)
                .orElse(new com.example.delparque.model.Pago())));
    }

    @Override
    public Pago save(Pago pago) {
        condominiosRepository.findById(pago.getIdCondominio()).orElseThrow();

        if (pago.getId() == null) {
            pago.setId(UUID.randomUUID().toString());
        }

        return PagosMapper.entityToDto(pagosRepository.save(PagosMapper.dtoToEntity(pago)));
    }
}

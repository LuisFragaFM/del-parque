package com.example.delparque.service.impl;

import com.example.delparque.dto.Pago;
import com.example.delparque.repository.CondominosRepository;
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
    private final CondominosRepository condominosRepository;

    public PagosServiceImpl(PagosRepository pagosRepository,
                            CondominosRepository condominosRepository) {
        this.pagosRepository = pagosRepository;
        this.condominosRepository = condominosRepository;
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
        condominosRepository.findById(pago.getIdCondominio()).orElseThrow();

        if (pago.getId() == null) {
            pago.setId(UUID.randomUUID().toString());
        }

        return PagosMapper.entityToDto(pagosRepository.save(PagosMapper.dtoToEntity(pago)));
    }
}

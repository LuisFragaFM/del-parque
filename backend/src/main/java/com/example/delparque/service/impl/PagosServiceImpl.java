package com.example.delparque.service.impl;

import com.example.delparque.dto.Pago;
import com.example.delparque.model.Condominio;
import com.example.delparque.repository.CondominiosRepository;
import com.example.delparque.repository.PagosRepository;
import com.example.delparque.service.PagosService;
import com.example.delparque.service.mappers.CondominiosMapper;
import com.example.delparque.service.mappers.PagosMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagosServiceImpl implements PagosService {

    private final PagosRepository pagosRepository;
    private final CondominiosRepository condominiosRepository;

    public PagosServiceImpl(PagosRepository pagosRepository, CondominiosRepository condominiosRepository) {
        this.pagosRepository = pagosRepository;
        this.condominiosRepository = condominiosRepository;
    }

    @Override
    public List<Pago> findAll() {
        return pagosRepository.findAll().stream()
                .map(p -> {
                    Pago pago = PagosMapper.entityToDto(p);

                    System.out.println(pago);
                    Condominio condominio = condominiosRepository.findById(p.getIdCondominio()).orElseThrow();

                    pago.setCondominio(CondominiosMapper.entityToDto(condominio));

                    return pago;
                }).collect(Collectors.toList());
    }
}

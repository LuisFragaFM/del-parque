package com.example.delparque.service.impl;

import com.example.delparque.dto.Paquete;
import com.example.delparque.repository.PaquetesRepository;
import com.example.delparque.service.PaquetesService;
import com.example.delparque.service.mappers.PaqueteMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaquetesServiceImpl implements PaquetesService {

    private final PaquetesRepository paquetesRepository;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    PaquetesServiceImpl(PaquetesRepository paquetesRepository, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.paquetesRepository = paquetesRepository;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
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

        String query = "SELECT * FROM paquetes";

        Pageable pageable = PageRequest.of(page, 10);
        BeanPropertyRowMapper<Paquete> paquetesViewMapper = new BeanPropertyRowMapper<>(Paquete.class);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        List<Paquete> paquetes = namedParameterJdbcTemplate.query(query +
                " LIMIT " + pageable.getPageSize() +
                " OFFSET " + pageable.getOffset(), mapSqlParameterSource, paquetesViewMapper);

        return new PageImpl<>(paquetes, pageable, 10);
    }

    @Override
    public Paquete findById(String id) {
        return paquetesRepository.findById(id).map(PaqueteMapper::entityToDto).orElse(null);
    }

    @Override
    public void delete(String id) {
        paquetesRepository.deleteById(id);
    }
}

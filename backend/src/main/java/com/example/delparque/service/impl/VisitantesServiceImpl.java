package com.example.delparque.service.impl;

import com.example.delparque.dto.Visitante;
import com.example.delparque.repository.VisitantesRepository;
import com.example.delparque.service.VisitantesService;
import com.example.delparque.service.mappers.VisitanteMapper;
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
public class VisitantesServiceImpl implements VisitantesService {

    private final VisitantesRepository visitantesRepository;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    VisitantesServiceImpl(VisitantesRepository visitantesRepository,
                          NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.visitantesRepository = visitantesRepository;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Visitante findById(String id) {
        return visitantesRepository.findById(id).map(VisitanteMapper::entityToDto).orElse(null);
    }

    @Override
    public Page<Visitante> findAllUnauthorized(Integer page) {
        String query = "SELECT * FROM visitantes v WHERE v.authorized = false";

        return getVisitantes(page, query);
    }

    @Override
    public Page<Visitante> findAllNoLeft(Integer page) {
        String query = "SELECT * FROM visitantes v WHERE v.gone_out = false AND v.authorized = true";

        return getVisitantes(page, query);
    }

    private Page<Visitante> getVisitantes(Integer page, String query) {
        Pageable pageable = PageRequest.of(page, 10);
        BeanPropertyRowMapper<Visitante> trabajadoresViewMapper = new BeanPropertyRowMapper<>(Visitante.class);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        List<Visitante> visitantes = namedParameterJdbcTemplate.query(query +
                " LIMIT " + pageable.getPageSize() +
                " OFFSET " + pageable.getOffset(), mapSqlParameterSource, trabajadoresViewMapper);

        return new PageImpl<>(visitantes, pageable, pageable.getPageSize());
    }

    @Override
    public Visitante save(Visitante visitante) {
        if (visitante.getId() == null) {
            visitante.setId(UUID.randomUUID().toString());
        }

        return VisitanteMapper.entityToDto(visitantesRepository.save(VisitanteMapper.dtoToEntity(visitante)));
    }

    @Override
    public void delete(String id) {
        visitantesRepository.deleteById(id);
    }

    @Override
    public Visitante findByName(String name) {
        return VisitanteMapper.entityToDto(visitantesRepository
                .findByName(name).orElse(new com.example.delparque.model.Visitante()));
    }
}

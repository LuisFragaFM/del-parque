package com.example.delparque.service.impl;

import com.example.delparque.dto.Visitante;
import com.example.delparque.exception.BadRequestDataException;
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
    public Page<Visitante> findAll(Integer page) {
        String query = "SELECT * FROM visitantes";

        Pageable pageable = PageRequest.of(page, 10);
        BeanPropertyRowMapper<Visitante> trabajadoresViewMapper = new BeanPropertyRowMapper<>(Visitante.class);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        List<Visitante> visitantes = namedParameterJdbcTemplate.query(query +
                " LIMIT " + pageable.getPageSize() +
                " OFFSET " + pageable.getOffset(), mapSqlParameterSource, trabajadoresViewMapper);

        return new PageImpl<>(visitantes, pageable, 10);
    }

    @Override
    public Visitante save(Visitante visitante) {
        if (visitante.getId() == null) {
            visitante.setId(UUID.randomUUID().toString());
        }

        if (visitante.getNombreVisitante() == null) {
            throw new BadRequestDataException("nombre requerido", "NAME_VISIT_ERROR");
        }

        if (visitante.getTarjetonVisitante() == null) {
            throw new BadRequestDataException("tarjeton requerido", "CARD_VISIT_ERROR");
        }

        if (visitante.getVehiculoVisitante() == null) {
            throw new BadRequestDataException("vehiculo requerido", "CAR_TYPE_ERROR");
        }

        if (visitante.getPlacasVehiculo() == null) {
            throw new BadRequestDataException("placas requerido", "PLATES_VISIT_ERROR");
        }
        if (visitante.getFechaLlegada() == null) {
            throw new BadRequestDataException("fecha requerido", "DATE_ERROR");
        }
        if (visitante.getHoraLlegada() == null) {
            throw new BadRequestDataException("hora requerido", "TIME_ERROR");
        }
        if (visitante.getAutorizacion() == null) {
            throw new BadRequestDataException("autorizacion requerido", "AUTHORIZATION_ERROR");
        }
        if (visitante.getCasetaLlegada() == null) {
            throw new BadRequestDataException("caseta requerido", "ARRIVED_STAND_ERROR");
        }

        return VisitanteMapper.entityToDto(visitantesRepository.save(VisitanteMapper.dtoToEntity(visitante)));
    }

    @Override
    public void delete(String id) {
        visitantesRepository.deleteById(id);
    }
}

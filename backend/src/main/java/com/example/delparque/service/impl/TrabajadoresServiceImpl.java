package com.example.delparque.service.impl;

import com.example.delparque.dto.Trabajador;
import com.example.delparque.exception.BadRequestDataException;
import com.example.delparque.repository.TrabajadoresRepository;
import com.example.delparque.service.TrabajadoresService;
import com.example.delparque.service.mappers.TrabajadorMapper;
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
public class TrabajadoresServiceImpl implements TrabajadoresService {

    private final TrabajadoresRepository trabajadoresRepository;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    TrabajadoresServiceImpl(TrabajadoresRepository trabajadoresRepository,
                            NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.trabajadoresRepository = trabajadoresRepository;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Page<Trabajador> findAll(Integer page) {

        String query = "SELECT * FROM trabajadores";

        Pageable pageable = PageRequest.of(page, 10);
        BeanPropertyRowMapper<Trabajador> trabajadoresViewMapper = new BeanPropertyRowMapper<>(Trabajador.class);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        List<Trabajador> trabajadores = namedParameterJdbcTemplate.query(query +
                " LIMIT " + pageable.getPageSize() +
                " OFFSET " + pageable.getOffset(), mapSqlParameterSource, trabajadoresViewMapper);

        return new PageImpl<>(trabajadores, pageable, 10);
    }

    @Override
    public Trabajador findById(String id) {
        return trabajadoresRepository.findById(id).map(TrabajadorMapper::entityToDto).orElse(null);
    }

    @Override
    public Trabajador findByName(String name) {
        return TrabajadorMapper.entityToDto(trabajadoresRepository.findTrabajadorBynombreTrabajador(name));
    }

    @Override
    public Trabajador save(Trabajador trabajador) {
        if (trabajador.getId() == null) {
            trabajador.setId(UUID.randomUUID().toString());
        }

        if(trabajador.getTipo() == null) {
            throw new BadRequestDataException("tipo requerido", "TYPE_WORKER_ERROR");
        }

        if(trabajador.getNombreTrabajador() == null) {
            throw new BadRequestDataException("nombre requerido", "NAME_WORKER_ERROR");
        }

        if(trabajador.getNombreCondomino() == null) {
            throw new BadRequestDataException("nombre_condomino requerido", "NAME_COND_ERROR");
        }

        if(trabajador.getDiasTrabajo() == null) {
            throw new BadRequestDataException("dias requerido", "DAYS_WORK_ERROR");
        }

        if(trabajador.getHorario() == null) {
            throw new BadRequestDataException("hora requerido", "ARRIVE_WORKER_ERROR");
        }

        if(trabajador.getTelefono() == null) {
            throw new BadRequestDataException("telefono requerido", "NUMBER_WORKER_ERROR");
        }

        return TrabajadorMapper.entityToDto(trabajadoresRepository.save(TrabajadorMapper.dtoToEntity(trabajador)));
    }

    @Override
    public void delete(String id) {
        trabajadoresRepository.deleteById(id);
    }
}

package com.example.delparque.service.impl;

import com.example.delparque.dto.Package;
import com.example.delparque.repository.PaquetesRepository;
import com.example.delparque.service.PackageService;
import com.example.delparque.service.mappers.PackageMapper;
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
public class PackageServiceImpl implements PackageService {

    private final PaquetesRepository paquetesRepository;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    PackageServiceImpl(PaquetesRepository paquetesRepository, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.paquetesRepository = paquetesRepository;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Package save(Package aPackage) {
        if (aPackage.getId() == null) {
            aPackage.setId(UUID.randomUUID().toString());
        }

        return PackageMapper.entityToDto(paquetesRepository.save(PackageMapper.dtoToEntity(aPackage)));
    }

    @Override
    public Page<Package> findAll(Integer page) {
        String query = "SELECT * FROM paquetes p WHERE p.entregado = false";

        BeanPropertyRowMapper<Package> paquetesViewMapper = new BeanPropertyRowMapper<>(Package.class);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        Pageable pageable = PageRequest.of(page, 10);

        List<Package> aPackages = namedParameterJdbcTemplate.query(query +
                " LIMIT " + pageable.getPageSize() +
                " OFFSET " + pageable.getOffset(), mapSqlParameterSource, paquetesViewMapper);

        return new PageImpl<>(aPackages, pageable, pageable.getPageSize());
    }

    @Override
    public Package findById(String id) {
        return paquetesRepository.findById(id).map(PackageMapper::entityToDto).orElse(null);
    }

    @Override
    public void delete(String id) {
        paquetesRepository.deleteById(id);
    }
}

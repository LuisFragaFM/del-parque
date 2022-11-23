package com.example.delparque.service;

import com.example.delparque.dto.Condomino;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface CondominosService {
    Page<Condomino> findAll(Integer page);

    Condomino findById(String id);

    List<Condomino> findByName(String name);

    Optional<Condomino> findByStreetAndHouseNumber(String street, String number);

    Condomino save(Condomino condomino);

    void delete(String id);
}

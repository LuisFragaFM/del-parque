package com.example.delparque.service;

import com.example.delparque.dto.Paquete;
import org.springframework.data.domain.Page;

public interface PackageService {
    Paquete save(Paquete paquete);

    Page<Paquete> findAll(Integer page);

    Paquete findById(String id);

    void delete(String id);
}

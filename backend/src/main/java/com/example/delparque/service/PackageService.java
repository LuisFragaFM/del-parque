package com.example.delparque.service;

import com.example.delparque.dto.Package;
import org.springframework.data.domain.Page;

public interface PackageService {
    Package save(Package aPackage);

    Page<Package> findAll(Integer page);

    Package findById(String id);

    void delete(String id);
}

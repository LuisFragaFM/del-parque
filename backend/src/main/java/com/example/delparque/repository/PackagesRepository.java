package com.example.delparque.repository;

import com.example.delparque.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackagesRepository extends JpaRepository<Package, String> {
}

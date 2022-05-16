package com.example.delparque.repository;

import com.example.delparque.model.Paquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaquetesRepository extends JpaRepository<Paquete, String> {
}

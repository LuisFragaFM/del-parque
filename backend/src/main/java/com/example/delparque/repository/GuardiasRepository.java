package com.example.delparque.repository;

import com.example.delparque.model.Guardia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuardiasRepository extends JpaRepository<Guardia, String> {
}

package com.example.delparque.repository;

import com.example.delparque.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregasRepository extends JpaRepository<Entrega, String> {
}

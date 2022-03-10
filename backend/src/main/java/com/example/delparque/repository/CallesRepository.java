package com.example.delparque.repository;

import com.example.delparque.model.Calle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallesRepository extends JpaRepository<Calle, String> {
}

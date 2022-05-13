package com.example.delparque.repository;

import com.example.delparque.model.Condomino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondominosRepository extends JpaRepository<Condomino, String> {
}

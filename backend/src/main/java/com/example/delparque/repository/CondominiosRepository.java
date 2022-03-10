package com.example.delparque.repository;

import com.example.delparque.model.Condominio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondominiosRepository extends JpaRepository<Condominio, String> {
}

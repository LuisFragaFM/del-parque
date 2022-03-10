package com.example.delparque.repository;

import com.example.delparque.model.Automovil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomovilesRepository extends JpaRepository<Automovil, String> {

}

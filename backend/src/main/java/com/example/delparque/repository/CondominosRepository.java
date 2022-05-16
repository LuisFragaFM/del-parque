package com.example.delparque.repository;

import com.example.delparque.model.Condomino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CondominosRepository extends JpaRepository<Condomino, String> {

    Optional<Condomino> findByNumeroTelefono(String numeroTelefono);

    Optional<Condomino> findByNombre(String nombre);
}

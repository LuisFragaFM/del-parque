package com.example.delparque.repository;

import com.example.delparque.model.Condomino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CondominosRepository extends JpaRepository<Condomino, String> {

    Optional<Condomino> findByNumeroTelefono(String numeroTelefono);

    @Query(value = "select * from condominos c where c.nombre like %:name%", nativeQuery = true)
    List<Condomino> findByName(String name);
}

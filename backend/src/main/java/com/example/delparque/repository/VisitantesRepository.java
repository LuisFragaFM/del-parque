package com.example.delparque.repository;

import com.example.delparque.model.Visitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitantesRepository extends JpaRepository<Visitante, String> {

    @Query(value = "select v.* from visitantes v where v.name like concat('%', :name, '%')", nativeQuery = true)
    List<Visitante> findByName(String name);

    List<Visitante> findAllByAuthorizedIsAndUserId(boolean authorized, String userId);

    List<Visitante> findAllByAuthorizedIsAndGoneOutIs(boolean authorized, boolean goneOut);
}

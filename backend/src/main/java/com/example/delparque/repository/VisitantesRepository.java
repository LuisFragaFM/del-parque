package com.example.delparque.repository;

import com.example.delparque.model.Visitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VisitantesRepository extends JpaRepository<Visitante, String> {

    List<Visitante> findAllByUserId(String userId);

    List<Visitante> findAllByAuthorizedIsAndUserId(boolean authorized, String userId);

    List<Visitante> findAllByAuthorizedIsAndArrivalDateIs(boolean authorized, LocalDate arrivalDate);

    List<Visitante> findAllByCheckOutIs(boolean checkOut);
}

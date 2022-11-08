package com.example.delparque.repository;

import com.example.delparque.model.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TrabajadoresRepository extends JpaRepository<Trabajador, String> {
    @Query(value = "select t.* from trabajadores t where t.name like concat('%', :name, '%')", nativeQuery = true)
    List<Trabajador> findByName(String name);
}
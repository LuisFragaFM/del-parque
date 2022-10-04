package com.example.delparque.repository;

import com.example.delparque.model.Condomino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CondominosRepository extends JpaRepository<Condomino, String> {
    @Query(value = "select * from condominos c where c.name like %:name%", nativeQuery = true)
    List<Condomino> findByName(String name);
}

package com.example.delparque.repository;

import com.example.delparque.model.Condomino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CondominosRepository extends JpaRepository<Condomino, String> {
    @Query(value = "select c.*, u.id from users u right join condominos c on c.user_id = u.id where lower(u.name) like lower(concat('%', :name, '%'))", nativeQuery = true)
    List<Condomino> findAllByName(String name);

    Optional<Condomino> findByUserId(String s);
}
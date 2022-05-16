package com.example.delparque.repository;

import com.example.delparque.model.RolesPorUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolesRepository extends JpaRepository<RolesPorUsuario, String> {

    List<RolesPorUsuario> findAllByIdUsuario(String id);
}

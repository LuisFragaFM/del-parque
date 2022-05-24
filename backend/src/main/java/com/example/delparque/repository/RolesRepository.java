package com.example.delparque.repository;

import com.example.delparque.model.RolesPorUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolesRepository extends JpaRepository<RolesPorUsuario, String> {

    @Query("SELECT r.role FROM RolesPorUsuario r WHERE r.idUsuario = :user")
    List<String> findRolesByUser(String user);
}

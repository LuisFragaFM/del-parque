package com.example.delparque.repository;

import com.example.delparque.model.RolesByUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolesRepository extends JpaRepository<RolesByUser, String> {

    @Query("SELECT r.role FROM RolesByUser r WHERE r.idUsuario = :user")
    List<String> findRolesByUser(String user);

    void deleteRolesPorUsuarioByIdUsuarioAndRole(String userId, String role);
}

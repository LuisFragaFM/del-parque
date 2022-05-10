package com.example.delparque.repository;

import com.example.delparque.model.RoleByUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<RoleByUser, String> {

    List<RoleByUser> findAllByUserId(String userId);
}

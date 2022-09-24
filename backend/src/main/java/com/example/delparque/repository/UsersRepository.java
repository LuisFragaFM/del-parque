package com.example.delparque.repository;

import com.example.delparque.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

}

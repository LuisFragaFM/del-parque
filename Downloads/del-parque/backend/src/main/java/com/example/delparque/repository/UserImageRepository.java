package com.example.delparque.repository;

import com.example.delparque.model.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserImageRepository extends JpaRepository<UserImage, String> {
    Optional<UserImage> findByUserId(String userId);
}

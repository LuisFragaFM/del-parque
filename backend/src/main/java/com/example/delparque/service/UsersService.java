package com.example.delparque.service;

import com.example.delparque.model.User;

import java.util.List;

public interface UsersService {

    User findUserByEmailAndPassword(String email, String password);

    User register(com.example.delparque.dto.User user);

    com.example.delparque.dto.User buildUserForEmail(String email);

    List<com.example.delparque.dto.User> getAllUsers();

    List<String> removeRole(String userId, String role);

    List<String> addRole(String userId, String role);
}

package com.example.delparque.service;

import com.example.delparque.model.Usuario;

import java.util.List;

public interface UsuariosService {
    Usuario findOrRegister(String email, String password);

    com.example.delparque.dto.Usuario buildUserForEmail(String email);

    List<com.example.delparque.dto.Usuario> getAllUsers();

    List<String> removeRole(String userId, String role);

    List<String> addRole(String userId, String role);
}

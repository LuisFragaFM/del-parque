package com.example.delparque.service;

import com.example.delparque.model.Usuario;

public interface UsuariosService {
    Usuario findOrRegister(String email, String password);

    com.example.delparque.dto.Usuario buildUserForEmail(String email);
}

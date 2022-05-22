package com.example.delparque.service;

import com.example.delparque.model.Usuario;

public interface UsuariosService {
    Usuario findOrRegister(String email, String password);
}

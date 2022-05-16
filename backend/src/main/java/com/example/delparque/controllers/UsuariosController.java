package com.example.delparque.controllers;

import com.example.delparque.model.Usuario;
import com.example.delparque.service.UsuariosService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/usuarios")
public class UsuariosController {

    private final UsuariosService usuariosService;

    UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @PostMapping()
    public void register(@RequestBody Usuario usuario) {
        usuariosService.register(usuario);
    }
}

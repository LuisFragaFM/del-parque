package com.example.delparque.controllers;

import com.example.delparque.dto.Usuario;
import com.example.delparque.service.UsuariosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UsuariosController {

    private final UsuariosService usuariosService;
    private final SessionHelper sessionHelper;

    UsuariosController(UsuariosService usuariosService, SessionHelper sessionHelper) {
        this.usuariosService = usuariosService;
        this.sessionHelper = sessionHelper;
    }

    @GetMapping("/api/loggedUser")
    public Usuario getLoginInfo(Principal principal) {
        String email = sessionHelper.getEmailForLoggedUser(principal);

        return usuariosService.buildUserForEmail(email);
    }
}

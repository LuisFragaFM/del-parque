package com.example.delparque.controllers;

import com.example.delparque.dto.Usuario;
import com.example.delparque.service.UsuariosService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UsuariosController {

    private final UsuariosService usuariosService;
    private final SessionHelper sessionHelper;

    UsuariosController(UsuariosService usuariosService, SessionHelper sessionHelper) {
        this.usuariosService = usuariosService;
        this.sessionHelper = sessionHelper;
    }

    @GetMapping("/api/users")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Usuario> getAllUsers() {
        return usuariosService.getAllUsers();
    }

    @GetMapping("/api/loggedUser")
    public Usuario getLoginInfo(Principal principal) {
        String email = sessionHelper.getEmailForLoggedUser(principal);

        return usuariosService.buildUserForEmail(email);
    }

    @PatchMapping("/api/users/{userId}/{role}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<String> addRole(@PathVariable String userId, @PathVariable String role) {
        return usuariosService.addRole(userId, role);
    }

    @DeleteMapping("/api/users/{userId}/{role}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<String> removeRole(@PathVariable String userId, @PathVariable String role) {
        return usuariosService.removeRole(userId, role);
    }
}

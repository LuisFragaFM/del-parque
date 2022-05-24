package com.example.delparque.service.impl;

import com.example.delparque.model.Login;
import com.example.delparque.model.Usuario;
import com.example.delparque.repository.LoginRepository;
import com.example.delparque.repository.RolesRepository;
import com.example.delparque.repository.UsuariosRepository;
import com.example.delparque.service.UsuariosService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuariosServiceImpl implements UsuariosService {
    private final LoginRepository loginRepository;

    private final UsuariosRepository usuariosRepository;

    private final RolesRepository rolesRepository;

    public UsuariosServiceImpl(UsuariosRepository usuariosRepository, LoginRepository loginRepository, RolesRepository rolesRepository) {
        this.usuariosRepository = usuariosRepository;
        this.loginRepository = loginRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    @Transactional
    public Usuario findOrRegister(String email, String name) {
        return usuariosRepository.findByEmail(email).or(() -> registerUser(email, name)).orElseThrow();
    }

    @Override
    public com.example.delparque.dto.Usuario buildUserForEmail(String email) {
        Usuario user = usuariosRepository.findByEmail(email).orElseThrow();

        return com.example.delparque.dto.Usuario.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nombre(user.getNombre())
                .roles(rolesRepository.findRolesByUser(user.getId()))
                .build();
    }

    private Optional<Usuario> registerUser(String email, String name) {
        Usuario user = Usuario.builder()
                .id(UUID.randomUUID().toString())
                .nombre(name)
                .email(email)
                .build();

        usuariosRepository.save(user);

        Login login = new Login();

        login.setUserId(user.getId());
        login.setEmail(email);
        login.setId(UUID.randomUUID().toString());

        loginRepository.save(login);

        return Optional.of(user);
    }
}

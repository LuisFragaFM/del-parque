package com.example.delparque.service.impl;

import com.example.delparque.model.Login;
import com.example.delparque.model.Usuario;
import com.example.delparque.repository.LoginRepository;
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

    public UsuariosServiceImpl(UsuariosRepository usuariosRepository, LoginRepository loginRepository) {
        this.usuariosRepository = usuariosRepository;
        this.loginRepository = loginRepository;
    }

    @Override
    @Transactional
    public Usuario findOrRegister(String email, String name) {
        return usuariosRepository.findByEmail(email).or(() -> registerUser(email, name)).orElseThrow();
    }

    private Optional<Usuario> registerUser(String email, String name) {
        Usuario user = Usuario.builder()
                .id(UUID.randomUUID().toString())
                .nombre(name)
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

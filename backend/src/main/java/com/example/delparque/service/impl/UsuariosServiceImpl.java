package com.example.delparque.service.impl;

import com.example.delparque.exception.BadRequestDataException;
import com.example.delparque.model.Usuario;
import com.example.delparque.repository.UsuariosRepository;
import com.example.delparque.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuariosServiceImpl implements UsuariosService {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    private final UsuariosRepository usuariosRepository;

    public UsuariosServiceImpl(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public void register(com.example.delparque.model.Usuario user) {

        Usuario username = usuariosRepository.findByUsername(user.getUsername());

        Usuario email = usuariosRepository.findByEmail(user.getEmail());

        if (username != null) {
            throw new BadRequestDataException("usuario en uso", "BAD_USERNAME_REGISTER");
        }

        if (email != null) {
            throw new BadRequestDataException("correo electronico en uso", "BAD_EMAIL_REGISTER");
        }

        if (user.getId() == null) {
            user.setId(UUID.randomUUID().toString());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        usuariosRepository.save(user);
    }
}

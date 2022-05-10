package com.example.delparque.service.impl;

import com.example.delparque.exception.BadRequestDataException;
import com.example.delparque.model.User;
import com.example.delparque.repository.UserRepository;
import com.example.delparque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(com.example.delparque.model.User user) {

        User username = userRepository.findByUsername(user.getUsername());

        User email = userRepository.findByEmail(user.getEmail());

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

        userRepository.save(user);
    }
}

package com.example.delparque.service.impl;

import com.example.delparque.exception.DelParqueSystemException;
import com.example.delparque.model.RolesByUser;
import com.example.delparque.model.User;
import com.example.delparque.repository.RolesRepository;
import com.example.delparque.repository.UsersRepository;
import com.example.delparque.service.UsersService;
import com.example.delparque.service.mappers.UserMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final RolesRepository rolesRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository,
                            RolesRepository rolesRepository,
                            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        User user = usersRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Wrong credentials"));

        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return user;
        }

        throw new UsernameNotFoundException("Wrong credentials");
    }

    @Override
    public User register(com.example.delparque.dto.User user) {
        Optional<User> optionalUser = usersRepository.findByEmail(user.getEmail());

        user.setId(UUID.randomUUID().toString());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        optionalUser.ifPresent(value -> {
            if (value.getEmail().equals(user.getEmail())) {
                throw new DelParqueSystemException("email ocupado por otro usuario", "DUPLICATE_EMAIL");
            }
        });

        usersRepository.save(UserMapper.dtoToEntity(user));

        return UserMapper.dtoToEntity(user);
    }

    @Override
    public com.example.delparque.dto.User buildUserForEmail(String email) {
        User user = usersRepository.findByEmail(email).orElseThrow();

        return com.example.delparque.dto.User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nombre(user.getNombre())
                .roles(rolesRepository.findRolesByUser(user.getId()))
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<com.example.delparque.dto.User> getAllUsers() {
        return usersRepository.findAll().stream().map(UserMapper::entityToDto)
                .peek(user -> user.setRoles(rolesRepository.findRolesByUser(user.getId())))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<String> removeRole(String userId, String role) {

        rolesRepository.deleteRolesPorUsuarioByIdUsuarioAndRole(userId, role);

        return rolesRepository.findRolesByUser(userId);
    }

    @Override
    @Transactional
    public List<String> addRole(String userId, String role) {
        RolesByUser roleByUser = new RolesByUser();

        roleByUser.setId(UUID.randomUUID().toString());
        roleByUser.setRole(role);
        roleByUser.setIdUsuario(userId);

        rolesRepository.save(roleByUser);

        return rolesRepository.findRolesByUser(userId);
    }
}
package com.example.delparque.config;

import com.example.delparque.dto.LoggedUser;
import com.example.delparque.model.User;
import com.example.delparque.repository.RolesRepository;
import com.example.delparque.service.UsersService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class UserDetailsService {

    private final UsersService usersService;
    private final RolesRepository rolesRepository;

    public UserDetailsService(UsersService usersService, RolesRepository rolesRepository) {
        this.usersService = usersService;
        this.rolesRepository = rolesRepository;
    }

    public Authentication loadUser(String email, String password) {

        Map<String, Object> attributes = new HashMap<>();
        Set<GrantedAuthority> authorities = new HashSet<>();

        User user = usersService.findUserByEmailAndPassword(email, password);

        attributes.put("email", email);
        attributes.put("name", user.getName());
        attributes.put("userId", user.getId());

        rolesRepository.findRolesByUser(user.getId()).forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role)));

        LoggedUser loggedUser = LoggedUser.builder()
                .claims(attributes)
                .grantedAuthorities(authorities)
                .name(user.getName())
                .build();

        return new UsernamePasswordAuthenticationToken(loggedUser, user, authorities);
    }

}

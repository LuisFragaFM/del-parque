package com.example.delparque.config;

import com.example.delparque.dto.LoggedUser;
import com.example.delparque.model.Usuario;
import com.example.delparque.repository.RolesRepository;
import com.example.delparque.service.UsuariosService;
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
public class SystemUserDetailsService {

    private final UsuariosService usuariosService;
    private final RolesRepository rolesRepository;

    public SystemUserDetailsService(UsuariosService usuariosService, RolesRepository rolesRepository) {
        this.usuariosService = usuariosService;
        this.rolesRepository = rolesRepository;
    }

    public Authentication loadUser(String name, String password) {

        Map<String, Object> attributes = new HashMap<>();
        Set<GrantedAuthority> authorities = new HashSet<>();
        String email = name + "@" + password + ".com";

        attributes.put("email", email);
        attributes.put("name", name);

        Usuario user = usuariosService.findOrRegister(email, name);

        rolesRepository.findAllByIdUsuario(user.getId()).forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getRole())));

        attributes.put("userId", user.getId());

        LoggedUser loggedUser = LoggedUser.builder()
                .claims(attributes)
                .grantedAuthorities(authorities)
                .name(user.getNombre())
                .build();

        return new UsernamePasswordAuthenticationToken(loggedUser, user, authorities);
    }

}

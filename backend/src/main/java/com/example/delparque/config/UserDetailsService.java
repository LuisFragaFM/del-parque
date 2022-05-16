package com.example.delparque.config;

import com.example.delparque.model.Usuario;
import com.example.delparque.repository.RolesRepository;
import com.example.delparque.repository.UsuariosRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UsuariosRepository usuariosRepository;
    private final RolesRepository rolesRepository;

    public UserDetailsService(UsuariosRepository usuariosRepository, RolesRepository rolesRepository) {
        this.usuariosRepository = usuariosRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario user = usuariosRepository.findByUsername(username);

        Collection<GrantedAuthority> roles = new ArrayList<>();
        rolesRepository.findAllByIdUsuario(user.getId())
                .forEach(role -> roles.add(new SimpleGrantedAuthority(role.getRole())));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }

}

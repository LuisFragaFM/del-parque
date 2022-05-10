package com.example.delparque.config;

import com.example.delparque.model.User;
import com.example.delparque.repository.RolesRepository;
import com.example.delparque.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;

    public UserDetailsService(UserRepository userRepository, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        Collection<GrantedAuthority> roles = new ArrayList<>();
        rolesRepository.findAllByUserId(user.getId())
                .forEach(role -> roles.add(new SimpleGrantedAuthority(role.getRole())));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }

}

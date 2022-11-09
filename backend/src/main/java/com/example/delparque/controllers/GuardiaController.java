package com.example.delparque.controllers;

import com.example.delparque.dto.User;
import com.example.delparque.service.UsersService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/guardias")
public class GuardiaController {

    private final UsersService usersService;

    public GuardiaController(UsersService usersService) {

        this.usersService = usersService;
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public User save(@RequestBody User guardia) {
        guardia.setRole("ROLE_GUARD");
        return usersService.register(guardia);
    }

}

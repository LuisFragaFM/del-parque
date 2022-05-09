package com.example.delparque.controllers;

import com.example.delparque.model.User;
import com.example.delparque.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registro")
    public void register(@RequestBody User user) {
        userService.register(user);
    }
}

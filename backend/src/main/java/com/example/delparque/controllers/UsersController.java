package com.example.delparque.controllers;

import com.example.delparque.dto.ResetPassword;
import com.example.delparque.dto.User;
import com.example.delparque.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

@RestController
public class UsersController {

    private final UsersService usersService;
    private final SessionHelper sessionHelper;

    UsersController(UsersService usersService, SessionHelper sessionHelper) {
        this.usersService = usersService;
        this.sessionHelper = sessionHelper;
    }

    @GetMapping("/api/users/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESIDENT', 'ROLE_GUARD')")
    public User findById(@PathVariable String id) {
        return usersService.findById(id);
    }

    @GetMapping("/api/users")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return usersService.getAllUsers();
    }

    @PostMapping("/forgot_password")
    public void sendMailForRecoverPassword(HttpServletRequest httpServletRequest)
            throws MessagingException, UnsupportedEncodingException {
        usersService.sendMailForRecoverPassword(httpServletRequest);
    }

    @PostMapping("/reset_password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePasswordForRecoverPassword(@RequestParam(name = "token") String token, @RequestBody ResetPassword resetPassword) {
        usersService.updatePasswordForRecoverPassword(token, resetPassword);
    }

    @GetMapping("/api/loggedUser")
    public User getLoginInfo(Principal principal) {
        String email = sessionHelper.getEmailForLoggedUser(principal);

        return usersService.buildUserForEmail(email);
    }
}

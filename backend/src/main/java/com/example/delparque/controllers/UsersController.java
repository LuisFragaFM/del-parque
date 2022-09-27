package com.example.delparque.controllers;

import com.example.delparque.dto.ResetPassword;
import com.example.delparque.dto.User;
import com.example.delparque.service.UsersService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/api/users")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return usersService.getAllUsers();
    }

    @PostMapping("/api/users/register")
    public ResponseEntity<com.example.delparque.model.User> register(@RequestBody User user) {
        com.example.delparque.model.User register = usersService.register(user);

        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = register.getEmail() + ":" + register.getPassword();

        httpHeaders.set(HttpHeaders.AUTHORIZATION, "Basic " + new String(Base64.encodeBase64(auth.getBytes())));

        register.setPassword(null);

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(register);
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

    @PatchMapping("/api/users/{userId}/{role}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<String> addRole(@PathVariable String userId, @PathVariable String role) {
        return usersService.addRole(userId, role);
    }

    @DeleteMapping("/api/users/{userId}/{role}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<String> removeRole(@PathVariable String userId, @PathVariable String role) {
        return usersService.removeRole(userId, role);
    }
}

package com.example.delparque.service;

import com.example.delparque.dto.ResetPassword;
import com.example.delparque.model.User;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UsersService {
    User findUserByEmailAndPassword(String email, String password);

    void sendMailForRecoverPassword(HttpServletRequest httpServletRequest)
            throws MessagingException, UnsupportedEncodingException;

    void updatePasswordForRecoverPassword(String token, ResetPassword resetPassword);

    void register(com.example.delparque.dto.UserView user);

    com.example.delparque.dto.User buildUserForEmail(String email);

    List<com.example.delparque.dto.User> getAllUsers();

    List<String> removeRole(String userId, String role);

    List<String> addRole(String userId, String role);
}

package com.example.delparque.service;

import com.example.delparque.dto.ResetPassword;
import com.example.delparque.model.User;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UsersService {

    com.example.delparque.dto.User findById(String id);

    User findUserByEmailAndPassword(String email, String password);

    void sendMailForRecoverPassword(HttpServletRequest httpServletRequest)
            throws MessagingException, UnsupportedEncodingException;

    void updatePasswordForRecoverPassword(String token, ResetPassword resetPassword);

    com.example.delparque.dto.User register(com.example.delparque.dto.User user);

    com.example.delparque.dto.User buildUserForEmail(String email);

    List<com.example.delparque.dto.User> getAllUsers();
}

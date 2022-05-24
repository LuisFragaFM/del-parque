package com.example.delparque.controllers;

import com.example.delparque.config.Role;
import com.example.delparque.dto.LoggedUser;

import java.security.Principal;

public interface SessionHelper {
    String getEmailForLoggedUser(Principal principal);

    String getUserIdForLoggedUser(Principal principal);

    LoggedUser getLoggedUser(Principal principal);

    boolean hasRole(LoggedUser user, Role role);
}
package com.example.delparque.service.mappers;

import com.example.delparque.dto.User;

public class UserMapper {
    public static User entityToDto(com.example.delparque.model.User user) {
        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    public static com.example.delparque.model.User dtoToEntity(User user) {
        return com.example.delparque.model.User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

}

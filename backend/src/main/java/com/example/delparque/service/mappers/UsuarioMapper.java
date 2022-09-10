package com.example.delparque.service.mappers;

import com.example.delparque.dto.Trabajador;
import com.example.delparque.dto.Usuario;

public class UsuarioMapper {
    public static Usuario entityToDto(com.example.delparque.model.Usuario usuario) {
        return Usuario.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .nombre(usuario.getNombre())
                .build();
    }

    public static com.example.delparque.model.Usuario dtoToEntity(Usuario usuario) {
        return com.example.delparque.model.Usuario.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .nombre(usuario.getNombre())
                .build();
    }

}

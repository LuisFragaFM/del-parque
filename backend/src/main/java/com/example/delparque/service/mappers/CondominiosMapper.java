package com.example.delparque.service.mappers;

import com.example.delparque.dto.Condomino;

public class CondominiosMapper {

    public static Condomino entityToDto(com.example.delparque.model.Condomino condomino) {
        return Condomino.builder()
                .id(condomino.getId())
                .idUsuario(condomino.getIdUsuario())
                .idDireccion(condomino.getIdDireccion())
                .idTrabajador(condomino.getIdTrabajador())
                .build();
    }

    public static com.example.delparque.model.Condomino dtoToEntity(Condomino condominio) {
        return com.example.delparque.model.Condomino.builder()
                .id(condominio.getId())
                .idUsuario(condominio.getIdUsuario())
                .idDireccion(condominio.getIdDireccion())
                .idTrabajador(condominio.getIdTrabajador())
                .build();
    }
}

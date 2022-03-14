package com.example.delparque.service.mappers;

import com.example.delparque.dto.Condominio;

public class CondominiosMapper {

    public static Condominio entityToDto(com.example.delparque.model.Condominio condominio) {
        return Condominio.builder()
                .id(condominio.getId())
                .build();
    }

    public static com.example.delparque.model.Condominio dtoToEntity(Condominio condominio) {
        return com.example.delparque.model.Condominio.builder()
                .id(condominio.getId())
                .build();
    }
}

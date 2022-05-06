package com.example.delparque.service.mappers;

import com.example.delparque.dto.Calle;

public class CallesMapper {

    public static Calle entityToDto(com.example.delparque.model.Calle calle) {
        return Calle.builder()
                .id(calle.getId())
                .nombre(calle.getNombre())
                .build();
    }

    public static com.example.delparque.model.Calle dtoToEntity(Calle calle) {
        return com.example.delparque.model.Calle.builder()
                .id(calle.getId())
                .nombre(calle.getNombre())
                .build();
    }
}

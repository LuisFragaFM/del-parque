package com.example.delparque.service.mappers;

import com.example.delparque.dto.Guardia;

public class GuardiaMapper {

    public static Guardia entityToDto(com.example.delparque.model.Guardia guardia) {
        return Guardia.builder()
                .id(guardia.getId())
                .userId(guardia.getUserId())
                .build();
    }

    public static com.example.delparque.model.Guardia dtoToEntity(Guardia guardia) {
        return com.example.delparque.model.Guardia.builder()
                .id(guardia.getId())
                .userId(guardia.getUserId())
                .build();
    }
}

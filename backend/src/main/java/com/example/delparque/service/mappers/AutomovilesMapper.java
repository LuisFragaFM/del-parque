package com.example.delparque.service.mappers;

import com.example.delparque.dto.Automovil;

public class AutomovilesMapper {

    public static Automovil entityToDto(com.example.delparque.model.Automovil automovil) {
        return Automovil.builder()
                .id(automovil.getId())
                .color(automovil.getColor())
                .modelo(automovil.getModelo())
                .placas(automovil.getPlacas())
                .build();
    }

    public static com.example.delparque.model.Automovil dtoToEntity(Automovil automovil) {
        return com.example.delparque.model.Automovil.builder()
                .id(automovil.getId())
                .color(automovil.getColor())
                .modelo(automovil.getModelo())
                .placas(automovil.getPlacas())
                .build();
    }
}

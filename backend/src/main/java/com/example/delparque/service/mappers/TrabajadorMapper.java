package com.example.delparque.service.mappers;

import com.example.delparque.dto.Trabajador;

public class TrabajadorMapper {
    public static Trabajador entityToDto(com.example.delparque.model.Trabajador trabajador) {
        return Trabajador.builder()
                .id(trabajador.getId())
                .schedule(trabajador.getSchedule())
                .name(trabajador.getName())
                .telephoneNumber(trabajador.getTelephoneNumber())
                .type(trabajador.getType())
                .build();
    }

    public static com.example.delparque.model.Trabajador dtoToEntity(Trabajador trabajador) {
        return com.example.delparque.model.Trabajador.builder()
                .id(trabajador.getId())
                .schedule(trabajador.getSchedule())
                .name(trabajador.getName())
                .telephoneNumber(trabajador.getTelephoneNumber())
                .type(trabajador.getType())
                .condominoId(trabajador.getCondominoInfo().getCondominoId())
                .build();
    }
}

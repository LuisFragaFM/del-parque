package com.example.delparque.service.mappers;

import com.example.delparque.dto.Trabajador;

public class TrabajadorMapper {
    public static Trabajador entityToDto(com.example.delparque.model.Trabajador trabajador) {
        return Trabajador.builder()
                .id(trabajador.getId())
                .diasTrabajo(trabajador.getDiasTrabajo())
                .horario(trabajador.getHorario())
                .nombreTrabajador(trabajador.getNombreTrabajador())
                .nombreCondomino(trabajador.getNombreCondomino())
                .telefono(trabajador.getTelefono())
                .tipo(trabajador.getTipo())
                .build();
    }

    public static com.example.delparque.model.Trabajador dtoToEntity(Trabajador trabajador) {
        return com.example.delparque.model.Trabajador.builder()
                .id(trabajador.getId())
                .diasTrabajo(trabajador.getDiasTrabajo())
                .horario(trabajador.getHorario())
                .nombreTrabajador(trabajador.getNombreTrabajador())
                .nombreCondomino(trabajador.getNombreCondomino())
                .telefono(trabajador.getTelefono())
                .tipo(trabajador.getTipo())
                .build();
    }
}

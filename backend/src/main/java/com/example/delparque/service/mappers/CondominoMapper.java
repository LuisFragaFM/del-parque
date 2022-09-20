package com.example.delparque.service.mappers;

import com.example.delparque.dto.Condomino;

public class CondominoMapper {

    public static Condomino entityToDto(com.example.delparque.model.Condomino condomino) {
        return Condomino.builder()
                .id(condomino.getId())
                .calle(condomino.getCalle())
                .correoElectronico(condomino.getCorreoElectronico())
                .direccion(condomino.getDireccion())
                .name(condomino.getNombre())
                .numeroCasa(condomino.getNumeroCasa())
                .numeroEmergencia(condomino.getNumeroEmergencia())
                .numeroTelefono(condomino.getNumeroTelefono())
                .estadoPago(condomino.getEstadoPago())
                .build();
    }

    public static com.example.delparque.model.Condomino dtoToEntity(Condomino condomino) {
        return com.example.delparque.model.Condomino.builder()
                .id(condomino.getId())
                .calle(condomino.getCalle())
                .correoElectronico(condomino.getCorreoElectronico())
                .direccion(condomino.getDireccion())
                .nombre(condomino.getName())
                .numeroCasa(condomino.getNumeroCasa())
                .numeroEmergencia(condomino.getNumeroEmergencia())
                .numeroTelefono(condomino.getNumeroTelefono())
                .estadoPago(condomino.getEstadoPago())
                .build();
    }
}

package com.example.delparque.service.mappers;

import com.example.delparque.dto.Direccion;

public class DireccionesMapper {

    public static Direccion entityToDto(com.example.delparque.model.Direccion direccion) {
        return Direccion.builder()
                .id(direccion.getId())
                .idCalle(direccion.getIdCalle())
                .nombreColonia(direccion.getNombreColonia())
                .numeroCasa(direccion.getNumeroCasa())
                .numeroInterior(direccion.getNumeroInterior())
                .build();
    }

    public static com.example.delparque.model.Direccion dtoToEntity(Direccion direccion) {
        return com.example.delparque.model.Direccion.builder()
                .id(direccion.getId())
                .idCalle(direccion.getIdCalle())
                .nombreColonia(direccion.getNombreColonia())
                .numeroCasa(direccion.getNumeroCasa())
                .numeroInterior(direccion.getNumeroInterior())
                .build();
    }
}

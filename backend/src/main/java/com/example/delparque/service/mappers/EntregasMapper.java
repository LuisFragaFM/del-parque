package com.example.delparque.service.mappers;

import com.example.delparque.dto.Entrega;

public class EntregasMapper {

    public static Entrega entityToDto(com.example.delparque.model.Entrega entrega) {
        return Entrega.builder()
                .id(entrega.getId())
                .idPaquete(entrega.getIdPaquete())
                .quienEntrega(entrega.getQuienEntrega())
                .build();
    }

    public static com.example.delparque.model.Entrega dtoToEntity(Entrega entrega) {
        return com.example.delparque.model.Entrega
                .builder()
                .id(entrega.getId())
                .idPaquete(entrega.getIdPaquete())
                .quienEntrega(entrega.getQuienEntrega())
                .build();
    }
}

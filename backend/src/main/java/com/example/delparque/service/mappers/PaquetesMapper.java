package com.example.delparque.service.mappers;

import com.example.delparque.dto.Paquete;

public class PaquetesMapper {
    public static Paquete entityToDto(com.example.delparque.model.Paquete paquete) {
        return Paquete.builder()
                .id(paquete.getId())
                .caseta(paquete.getCaseta())
                .emisor(paquete.getEmisor())
                .receptor(paquete.getEmisor())
                .build();
    }

    public static com.example.delparque.model.Paquete dtoToEntity(Paquete paquete) {
        return com.example.delparque.model.Paquete.builder()
                .id(paquete.getId())
                .caseta(paquete.getCaseta())
                .emisor(paquete.getEmisor())
                .receptor(paquete.getEmisor())
                .build();
    }
}

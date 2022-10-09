package com.example.delparque.service.mappers;

import com.example.delparque.dto.Condomino;

public class CondominoMapper {

    public static Condomino entityToDto(com.example.delparque.model.Condomino condomino) {
        return Condomino.builder()
                .id(condomino.getId())
                .street(condomino.getStreet())
                .houseNumber(condomino.getHouseNumber())
                .userId(condomino.getUserId())
                .paidStatus(condomino.isPaidStatus())
                .userId(condomino.getUserId())
                .build();
    }

    public static com.example.delparque.model.Condomino dtoToEntity(Condomino condomino) {
        return com.example.delparque.model.Condomino.builder()
                .id(condomino.getId())
                .street(condomino.getStreet())
                .houseNumber(condomino.getHouseNumber())
                .userId(condomino.getUserId())
                .paidStatus(condomino.isPaidStatus())
                .userId(condomino.getUserId())
                .build();
    }
}

package com.example.delparque.service.mappers;

import com.example.delparque.dto.Pago;

public class PagosMapper {

    public static Pago entityToDto(com.example.delparque.model.Pago pago) {
        return Pago.builder()
                .id(pago.getId())
                .pagado(pago.getPagado())
                .build();
    }

    public static com.example.delparque.model.Pago dtoToEntity(Pago pago) {
        return com.example.delparque.model.Pago.builder()
                .id(pago.getId())
                .pagado(pago.getPagado())
                .build();
    }
}

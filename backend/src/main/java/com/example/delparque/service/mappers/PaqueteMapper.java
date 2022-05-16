package com.example.delparque.service.mappers;

import com.example.delparque.dto.Paquete;

public class PaqueteMapper {
    public static Paquete entityToDto(com.example.delparque.model.Paquete paquete) {
        return Paquete.builder()
                .id(paquete.getId())
                .casetaRecibida(paquete.getCasetaRecibida())
                .entrega(paquete.getEntrega())
                .entregaGuardia(paquete.getEntregaGuardia())
                .fechaEntrega(paquete.getFechaEntrega())
                .fechaLlegada(paquete.getFechaLlegada())
                .idCondomino(paquete.getIdCondomino())
                .nombreEmpresa(paquete.getNombreEmpresa())
                .numeroGuia(paquete.getNumeroGuia())
                .recibeGuardia(paquete.getRecibeGuardia())
                .recibeInquilino(paquete.getRecibeInquilino())
                .build();
    }

    public static com.example.delparque.model.Paquete dtoToEntity(Paquete paquete) {
        return com.example.delparque.model.Paquete.builder()
                .id(paquete.getId())
                .casetaRecibida(paquete.getCasetaRecibida())
                .entrega(paquete.getEntrega())
                .entregaGuardia(paquete.getEntregaGuardia())
                .fechaEntrega(paquete.getFechaEntrega())
                .fechaLlegada(paquete.getFechaLlegada())
                .idCondomino(paquete.getIdCondomino())
                .nombreEmpresa(paquete.getNombreEmpresa())
                .numeroGuia(paquete.getNumeroGuia())
                .recibeGuardia(paquete.getRecibeGuardia())
                .recibeInquilino(paquete.getRecibeInquilino())
                .build();
    }
}

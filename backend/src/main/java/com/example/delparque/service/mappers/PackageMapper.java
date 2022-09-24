package com.example.delparque.service.mappers;

import com.example.delparque.dto.Paquete;

public class PackageMapper {
    public static Paquete entityToDto(com.example.delparque.model.Paquete paquete) {
        return Paquete.builder()
                .id(paquete.getId())
                .casetaRecibida(paquete.getCasetaRecibida())
                .casetaEntrega(paquete.getCasetaEntrega())
                .entregaGuardia(paquete.getEntregaGuardia())
                .fechaEntrega(paquete.getFechaEntrega())
                .fechaLlegada(paquete.getFechaLlegada())
                .nombreCondomino(paquete.getNombreCondomino())
                .calle(paquete.getCalle())
                .numeroCasa(paquete.getNumeroCasa())
                .nombreEmpresa(paquete.getNombreEmpresa())
                .numeroGuia(paquete.getNumeroGuia())
                .recibeGuardia(paquete.getRecibeGuardia())
                .recibeInquilino(paquete.getRecibeInquilino())
                .horaEntrega(paquete.getHoraEntrega())
                .horaLlegada(paquete.getHoraLlegada())
                .build();
    }

    public static com.example.delparque.model.Paquete dtoToEntity(Paquete paquete) {
        return com.example.delparque.model.Paquete.builder()
                .id(paquete.getId())
                .casetaRecibida(paquete.getCasetaRecibida())
                .casetaEntrega(paquete.getCasetaEntrega())
                .entregaGuardia(paquete.getEntregaGuardia())
                .fechaEntrega(paquete.getFechaEntrega())
                .fechaLlegada(paquete.getFechaLlegada())
                .nombreCondomino(paquete.getNombreCondomino())
                .calle(paquete.getCalle())
                .numeroCasa(paquete.getNumeroCasa()).nombreEmpresa(paquete.getNombreEmpresa())
                .numeroGuia(paquete.getNumeroGuia())
                .recibeGuardia(paquete.getRecibeGuardia())
                .recibeInquilino(paquete.getRecibeInquilino())
                .horaEntrega(paquete.getHoraEntrega())
                .horaLlegada(paquete.getHoraLlegada())
                .build();
    }
}

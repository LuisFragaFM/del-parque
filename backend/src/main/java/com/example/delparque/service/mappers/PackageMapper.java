package com.example.delparque.service.mappers;

import com.example.delparque.dto.Package;

public class PackageMapper {
    public static Package entityToDto(com.example.delparque.model.Package aPackage) {
        return Package.builder()
                .id(aPackage.getId())
                .casetaRecibida(aPackage.getCasetaRecibida())
                .casetaEntrega(aPackage.getCasetaEntrega())
                .entregaGuardia(aPackage.getEntregaGuardia())
                .fechaEntrega(aPackage.getFechaEntrega())
                .fechaLlegada(aPackage.getFechaLlegada())
                .nombreCondomino(aPackage.getNombreCondomino())
                .calle(aPackage.getCalle())
                .numeroCasa(aPackage.getNumeroCasa())
                .nombreEmpresa(aPackage.getNombreEmpresa())
                .numeroGuia(aPackage.getNumeroGuia())
                .recibeGuardia(aPackage.getRecibeGuardia())
                .recibeInquilino(aPackage.getRecibeInquilino())
                .horaEntrega(aPackage.getHoraEntrega())
                .horaLlegada(aPackage.getHoraLlegada())
                .entregado(aPackage.isEntregado())
                .build();
    }

    public static com.example.delparque.model.Package dtoToEntity(Package aPackage) {
        return com.example.delparque.model.Package.builder()
                .id(aPackage.getId())
                .casetaRecibida(aPackage.getCasetaRecibida())
                .casetaEntrega(aPackage.getCasetaEntrega())
                .entregaGuardia(aPackage.getEntregaGuardia())
                .fechaEntrega(aPackage.getFechaEntrega())
                .fechaLlegada(aPackage.getFechaLlegada())
                .nombreCondomino(aPackage.getNombreCondomino())
                .calle(aPackage.getCalle())
                .numeroCasa(aPackage.getNumeroCasa()).nombreEmpresa(aPackage.getNombreEmpresa())
                .numeroGuia(aPackage.getNumeroGuia())
                .recibeGuardia(aPackage.getRecibeGuardia())
                .recibeInquilino(aPackage.getRecibeInquilino())
                .horaEntrega(aPackage.getHoraEntrega())
                .horaLlegada(aPackage.getHoraLlegada())
                .entregado(aPackage.isEntregado())
                .build();
    }
}

package com.example.delparque.service.mappers;

import com.example.delparque.dto.Visitante;

public class VisitanteMapper {

    public static Visitante entityToDto(com.example.delparque.model.Visitante visitante) {
        return Visitante.builder()
                .id(visitante.getId())
                .casetaSalida(visitante.getCasetaSalida())
                .autorizacion(visitante.getAutorizacion())
                .casetaLlegada(visitante.getCasetaLlegada())
                .fechaLlegada(visitante.getFechaLlegada())
                .fechaSalida(visitante.getFechaSalida())
                .horaLlegada(visitante.getHoraLlegada())
                .idCondomino(visitante.getIdCondomino())
                .horaSalida(visitante.getHoraSalida())
                .nombreVisitante(visitante.getNombreVisitante())
                .tarjetonVisitante(visitante.getTarjetonVisitante())
                .vehiculoVisitante(visitante.getVehiculoVisitante())
                .placasVehiculo(visitante.getPlacasVehiculo())
                .autorizada(visitante.isAutorizada())
                .salio(visitante.isSalio())
                .build();
    }

    public static com.example.delparque.model.Visitante dtoToEntity(Visitante visitante) {
        return com.example.delparque.model.Visitante.builder()
                .id(visitante.getId())
                .casetaSalida(visitante.getCasetaSalida())
                .autorizacion(visitante.getAutorizacion())
                .casetaLlegada(visitante.getCasetaLlegada())
                .fechaLlegada(visitante.getFechaLlegada())
                .fechaSalida(visitante.getFechaSalida())
                .horaLlegada(visitante.getHoraLlegada())
                .idCondomino(visitante.getIdCondomino())
                .horaSalida(visitante.getHoraSalida())
                .nombreVisitante(visitante.getNombreVisitante())
                .tarjetonVisitante(visitante.getTarjetonVisitante())
                .vehiculoVisitante(visitante.getVehiculoVisitante())
                .placasVehiculo(visitante.getPlacasVehiculo())
                .autorizada(visitante.isAutorizada())
                .salio(visitante.isSalio())
                .build();
    }
}

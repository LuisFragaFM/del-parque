package com.example.delparque.service.mappers;

import com.example.delparque.dto.Visitante;

public class VisitantesMapper {

    public static Visitante entityToDto(com.example.delparque.model.Visitante visitante) {
        return Visitante.builder()
                .id(visitante.getId())
                .nombre(visitante.getNombre())
                .casetaEntrada(visitante.getCasetaEntrada())
                .casetaSalida(visitante.getCasetaSalida())
                .idAutomovil(visitante.getIdAutomovil())
                .idCondominio(visitante.getIdCondominio())
                .fecha(visitante.getFecha())
                .vigilanteAutorizo(visitante.getVigilanteAutorizo())
                .residente(visitante.getResidente())
                .build();
    }

    public static com.example.delparque.model.Visitante dtoToEntity(Visitante visitante) {
        return com.example.delparque.model.Visitante.builder()
                .id(visitante.getId())
                .nombre(visitante.getNombre())
                .casetaEntrada(visitante.getCasetaEntrada())
                .casetaSalida(visitante.getCasetaSalida())
                .idAutomovil(visitante.getIdAutomovil())
                .idCondominio(visitante.getIdCondominio())
                .fecha(visitante.getFecha())
                .vigilanteAutorizo(visitante.getVigilanteAutorizo())
                .residente(visitante.getResidente())
                .build();
    }
}

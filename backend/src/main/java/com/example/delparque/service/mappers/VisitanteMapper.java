package com.example.delparque.service.mappers;

import com.example.delparque.dto.Visitante;

public class VisitanteMapper {

    public static Visitante entityToDto(com.example.delparque.model.Visitante visitante) {
        return Visitante.builder()
                .id(visitante.getId())
                .type_visitor(visitante.getType_visitor())
                .departureBooth(visitante.getDepartureBooth())
                .authorized(visitante.isAuthorized())
                .arrivalBooth(visitante.getArrivalBooth())
                .arrivalDate(visitante.getArrivalDate())
                .checkIn(visitante.getCheckIn())
                .card(visitante.getCard())
                .name(visitante.getName())
                .licensePlates(visitante.getLicensePlates())
                .vehicle(visitante.getVehicle())
                .departureDate(visitante.getDepartureDate())
                .departureTime(visitante.getDepartureTime())
                .authorization(visitante.getAuthorization())
                .checkOut(visitante.isCheckOut())
                .build();
    }

    public static com.example.delparque.model.Visitante dtoToEntity(Visitante visitante) {
        return com.example.delparque.model.Visitante.builder()
                .id(visitante.getId())
                .departureBooth(visitante.getDepartureBooth())
                .authorized(visitante.isAuthorized())
                .userId(visitante.getCondomino().getUserId())
                .arrivalBooth(visitante.getArrivalBooth())
                .arrivalDate(visitante.getArrivalDate())
                .checkIn(visitante.getCheckIn())
                .card(visitante.getCard())
                .name(visitante.getName())
                .licensePlates(visitante.getLicensePlates())
                .vehicle(visitante.getVehicle())
                .departureDate(visitante.getDepartureDate())
                .departureTime(visitante.getDepartureTime())
                .authorization(visitante.getAuthorization())
                .checkOut(visitante.isCheckOut())
                .userId(visitante.getCondomino().getUserId())
                .build();
    }
}

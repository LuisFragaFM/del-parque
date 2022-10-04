package com.example.delparque.service.mappers;

import com.example.delparque.dto.Visitante;

public class VisitanteMapper {

    public static Visitante entityToDto(com.example.delparque.model.Visitante visitante) {
        return Visitante.builder()
                .id(visitante.getId())
                .departureBooth(visitante.getDepartureBooth())
                .authorized(visitante.isAuthorized())
                .condominoId(visitante.getCondominoId())
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
                .goneOut(visitante.isGoneOut())
                .build();
    }

    public static com.example.delparque.model.Visitante dtoToEntity(Visitante visitante) {
        return com.example.delparque.model.Visitante.builder()
                .id(visitante.getId())
                .departureBooth(visitante.getDepartureBooth())
                .authorized(visitante.isAuthorized())
                .condominoId(visitante.getCondominoId())
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
                .goneOut(visitante.isGoneOut())
                .build();
    }
}

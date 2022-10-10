package com.example.delparque.service.mappers;

import com.example.delparque.dto.Paquete;

public class PaqueteMapper {
    public static Paquete entityToDto(com.example.delparque.model.Paquete paquete) {
        return Paquete.builder()
                .id(paquete.getId())
                .receivesBooth(paquete.getReceivesBooth())
                .deliveryBooth(paquete.getDeliveryBooth())
                .deliveryGuard(paquete.getDeliveryGuard())
                .deliveryDate(paquete.getDeliveryDate())
                .arrivalDate(paquete.getArrivalDate())
                .companyName(paquete.getCompanyName())
                .guideNumber(paquete.getGuideNumber())
                .receivesGuard(paquete.getReceivesGuard())
                .receivesResident(paquete.getReceivesResident())
                .deliveryTime(paquete.getDeliveryTime())
                .arrivalTime(paquete.getArrivalTime())
                .delivery(paquete.isDelivery())
                .build();
    }

    public static com.example.delparque.model.Paquete dtoToEntity(Paquete paquete) {
        return com.example.delparque.model.Paquete.builder()
                .id(paquete.getId())
                .receivesBooth(paquete.getReceivesBooth())
                .deliveryBooth(paquete.getDeliveryBooth())
                .deliveryGuard(paquete.getDeliveryGuard())
                .deliveryDate(paquete.getDeliveryDate())
                .arrivalDate(paquete.getArrivalDate())
                .companyName(paquete.getCompanyName())
                .guideNumber(paquete.getGuideNumber())
                .receivesGuard(paquete.getReceivesGuard())
                .receivesResident(paquete.getReceivesResident())
                .deliveryTime(paquete.getDeliveryTime())
                .arrivalTime(paquete.getArrivalTime())
                .delivery(paquete.isDelivery())
                .condominoId(paquete.getCondomino().getCondominoId())
                .build();
    }
}

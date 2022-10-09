package com.example.delparque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paquete {
    private String id;
    private String companyName;
    private String guideNumber;
    private String receivesGuard;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate deliveryDate;
    private String receivesBooth;
    private String deliveryBooth;
    private String receivesResident;
    private String deliveryGuard;
    private String condominoId;
    private String houseNumber;
    private String houseStreet;
    private String deliveryTime;
    private String arrivalTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate arrivalDate;
    private boolean delivery;
}
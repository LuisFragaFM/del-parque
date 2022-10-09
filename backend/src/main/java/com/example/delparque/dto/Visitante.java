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
public class Visitante {
    private String id;
    private String name;
    private String licensePlates;
    private String vehicle;
    private String card;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate arrivalDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate departureDate;
    private String checkIn;
    private String departureTime;
    private String authorization;
    private String departureBooth;
    private String arrivalBooth;
    private String condominoId;
    private String condominoName;
    private boolean authorized;
    private boolean goneOut;
}
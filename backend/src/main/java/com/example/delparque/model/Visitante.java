package com.example.delparque.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "visitantes")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visitante {
    @Id
    private String id;

    private String name;

    @Column(name = "license_plates")
    private String licensePlates;

    private String vehicle;

    private String card;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Column(name = "departure_date")
    private LocalDate  departureDate;

    @Column(name = "check_in")
    private String checkIn;

    @Column(name = "departure_time")
    private String departureTime;

    private String authorization;

    @Column(name = "departure_booth")
    private String departureBooth;

    @Column(name = "arrival_booth")
    private String arrivalBooth;

    @Column(name = "condomino_id")
    private String condominoId;

    private boolean authorized;

    @Column(name = "gone_out")
    private boolean goneOut;
}

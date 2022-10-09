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
@Table(name = "paquetes")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paquete {
    @Id
    private String id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "guide_number")
    private String guideNumber;

    @Column(name = "receives_guard")
    private String receivesGuard;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Column(name = "receives_booth")
    private String receivesBooth;

    @Column(name = "delivery_booth")
    private String deliveryBooth;

    @Column(name = "receives_resident")
    private String receivesResident;

    @Column(name = "delivery_guard")
    private String deliveryGuard;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "condomino_id")
    private String condominoId;

    @Column(name = "arrival_time")
    private String arrivalTime;

    @Column(name = "delivery_time")
    private String deliveryTime;

    private boolean delivery;
}

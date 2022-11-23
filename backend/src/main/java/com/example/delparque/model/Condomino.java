package com.example.delparque.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "condominos")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Condomino {
    @Id
    private String id;

    private String street;

    @Column(name = "relatives")

    private String relatives;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "paid_status")
    private boolean paidStatus;

    @Column(name = "user_id")
    private String userId;
}
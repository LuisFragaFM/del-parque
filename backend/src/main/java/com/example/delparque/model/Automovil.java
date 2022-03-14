package com.example.delparque.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "automoviles")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Automovil {
    @Id
    private String id;

    private String placas;

    private String color;

    private String modelo;

    private String tarjeta;
}

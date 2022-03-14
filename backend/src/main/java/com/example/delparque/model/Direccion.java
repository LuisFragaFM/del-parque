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
@Table(name = "direcciones")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {
    @Id
    private String id;

    private String numeroCasa;

    @Column(name = "id_casa")
    private String idCasa;

    private String nombreColonia;

    private String numeroInterior;
}

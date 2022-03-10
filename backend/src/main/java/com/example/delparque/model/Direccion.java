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
    @Column(name = "id_direccion")
    private String id;
    private String numeroCasa;
    private String idCasa;
    private String nombreColonia;
    private String numeroInterior;
}

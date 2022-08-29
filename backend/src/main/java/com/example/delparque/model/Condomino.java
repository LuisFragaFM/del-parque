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

    private String nombre;

    private String calle;

    @Column(name = "numero_casa")
    private String numeroCasa;

    @Column(name = "numero_telefono")
    private String numeroTelefono;

    private String direccion;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @Column(name = "numero_emergencia")
    private String numeroEmergencia;

    @Column(name = "estado_pago")
    private String estadoPago;
}
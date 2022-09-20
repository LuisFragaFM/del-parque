package com.example.delparque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Condomino {
    private String id;
    private String name;
    private String calle;
    private String numeroCasa;
    private String numeroTelefono;
    private String direccion;
    private String correoElectronico;
    private String numeroEmergencia;
    private String estadoPago;
}
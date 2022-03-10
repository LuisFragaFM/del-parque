package com.example.delparque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {
    private String id;
    private String numeroCasa;
    private Calle calle;
    private String nombreColonia;
    private String numeroInterior;
}

package com.example.delparque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Automovil {
    private String id;
    private String placas;
    private String color;
    private String modelo;
    private String tarjeta;
}

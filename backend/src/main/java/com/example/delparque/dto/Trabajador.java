package com.example.delparque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trabajador {
    private String id;
    private String nombre;
    private String fecha;
    private String caseta;
    private LocalDate hora;
}

package com.example.delparque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trabajador {
    private String id;
    private String tipo;
    private String nombreTrabajador;
    private String nombreCondomino;
    private String horario;
    private String telefono;
    private String idCondomino;
}

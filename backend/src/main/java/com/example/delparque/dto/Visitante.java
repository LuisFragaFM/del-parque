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
public class Visitante {
    private String id;
    private String idAutomovil;
    private String idCondominio;
    private String nombre;
    private LocalDate fecha;
    private String vigilanteAutorizo;
    private String residente;
    private String casetaEntrada;
    private String casetaSalida;
}

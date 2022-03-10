package com.example.delparque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visitante {
    private String id;
    private Automovil automovil;
    private Condominio condominio;
    private String nombre;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fecha;
    private String vigilanteAutorizo;
    private String residente;
    private String casetaEntrada;
    private String casetaSalida;
}

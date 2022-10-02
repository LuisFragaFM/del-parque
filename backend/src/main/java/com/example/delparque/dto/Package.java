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
public class Package {
    private String id;
    private String nombreEmpresa;
    private String numeroGuia;
    private String recibeGuardia;
    private LocalDate fechaLlegada;
    private String casetaRecibida;
    private String casetaEntrega;
    private String recibeInquilino;
    private String entregaGuardia;
    private LocalDate fechaEntrega;
    private String nombreCondomino;
    private String numeroCasa;
    private String calle;
    private String horaEntrega;
    private String horaLlegada;
    private boolean entregado;
}

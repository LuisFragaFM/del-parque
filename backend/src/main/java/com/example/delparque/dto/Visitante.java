package com.example.delparque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visitante {
    private String id;
    private String nombreVisitante;
    private String tarjetonVisitante;
    private String vehiculoVisitante;
    private String placasVehiculo;
    private String fechaLlegada;
    private String fechaSalida;
    private String horaLlegada;
    private String horaSalida;
    private String autorizacion;
    private String casetaLlegada;
    private String casetaSalida;
    private String idCondomino;
}
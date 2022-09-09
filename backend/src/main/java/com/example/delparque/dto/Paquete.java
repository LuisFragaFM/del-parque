package com.example.delparque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paquete {
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
}

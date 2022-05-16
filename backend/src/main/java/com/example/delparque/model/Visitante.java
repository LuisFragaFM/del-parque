package com.example.delparque.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "visitantes")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visitante {
    @Id
    private String id;

    @Column(name = "nombre_visitante")
    private String nombreVisitante;

    @Column(name = "tarjeton_visitante")
    private String tarjetonVisitante;

    @Column(name = "vehiculo_visitante")
    private String vehiculoVisitante;

    @Column(name = "placas_vehiculo")
    private String placasVehiculo;

    @Column(name = "fecha_llegada")
    private String fechaLlegada;

    @Column(name = "fecha_salida")
    private String fechaSalida;

    @Column(name = "hora_llegada")
    private String horaLlegada;

    @Column(name = "hora_salida")
    private String horaSalida;

    private String autorizacion;

    @Column(name = "caseta_llegada")
    private String casetaLlegada;

    @Column(name = "caseta_salida")
    private String casetaSalida;

    @Column(name = "id_condomino")
    private String idCondomino;
}

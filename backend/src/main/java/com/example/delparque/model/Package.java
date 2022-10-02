package com.example.delparque.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "paquetes")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Package {
    @Id
    private String id;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @Column(name = "numero_guia")
    private String numeroGuia;

    @Column(name = "recibe_guardia")
    private String recibeGuardia;

    @Column(name = "fecha_llegada")
    private LocalDate fechaLlegada;

    @Column(name = "caseta_recibida")
    private String casetaRecibida;

    @Column(name = "caseta_entrega")
    private String casetaEntrega;

    @Column(name = "recibe_inquilino")
    private String recibeInquilino;

    @Column(name = "entrega_guardia")
    private String entregaGuardia;

    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    @Column(name = "nombre_condomino")
    private String nombreCondomino;

    @Column(name = "numero_casa")
    private String numeroCasa;

    private String calle;

    @Column(name = "hora_llegada")
    private String horaLlegada;

    @Column(name = "hora_entrega")
    private String horaEntrega;

    private boolean entregado;
}

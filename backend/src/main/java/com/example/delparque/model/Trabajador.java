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
@Table(name = "trabajadores")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trabajador {
    @Id
    private String id;

    private String tipo;

    @Column(name = "nombre_trabajador")
    private String nombreTrabajador;

    @Column(name = "nombre_condomino")
    private String nombreCondomino;

    private String horario;

    private String telefono;

    @Column(name = "id_condomino")
    private String idCondomino;
}

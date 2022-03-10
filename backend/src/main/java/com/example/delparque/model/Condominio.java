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
@Table(name = "automoviles")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Condominio {
    @Id
    @Column(name = "id_condominio")
    private String id;
    private String idUsuario;
    private String idDireccion;
    private String idTrabajador;
}

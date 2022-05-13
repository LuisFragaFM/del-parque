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
@Table(name = "condominos")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Condomino {
    @Id
    private String id;

    @Column(name = "id_usuario")
    private String idUsuario;

    @Column(name = "id_direccion")
    private String idDireccion;

    @Column(name = "id_trabajador")
    private String idTrabajador;
}

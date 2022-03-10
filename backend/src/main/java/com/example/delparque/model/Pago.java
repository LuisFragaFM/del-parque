package com.example.delparque.model;

import com.example.delparque.dto.Condominio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pagos")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
    @Id
    @Column(name = "id_pagos")
    private String id;
    private Boolean pagado;
    private String idCondominio;
}

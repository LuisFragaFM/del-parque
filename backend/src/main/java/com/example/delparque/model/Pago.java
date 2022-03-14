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
@Table(name = "pagos")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
    @Id
    private String id;

    private Boolean pagado;

    @Column(name = "id_condominio")
    private String idCondominio;
}

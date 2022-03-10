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
@Table(name = "visitantes")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visitante {
    @Id
    @Column(name = "id_visitante")
    private String id;
    private String idAutomovil;
    private String idCondominio;
    private String nombre;
    private LocalDate fecha;
    private String vigilanteAutorizo;
    private String residente;
    private String casetaEntrada;
    private String casetaSalida;
}

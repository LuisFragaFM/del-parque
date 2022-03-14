package com.example.delparque.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "trabajadores")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trabajador {
    @Id
    private String id;
    private String nombre;

    private String fecha;

    private String caseta;

    private LocalDate hora;
}

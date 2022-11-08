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

    private String type;

    private String name;

    @Column(name = "condomino_id")
    private String condominoId;

    private String schedule;

    @Column(name = "telephone_number")
    private String telephoneNumber;
}

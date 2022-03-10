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
@Table(name = "calles")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calle {
    @Id
    @Column(name = "id_calle")
    private String id;
    private String nombre;
}

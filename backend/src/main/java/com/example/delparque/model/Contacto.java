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
@Table(name = "contactos")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contacto {
    @Id
    @Column(name = "id_contacto")
    private String id;
    private String email;
    private String numeroTelefono;
    private String telefonoEmergencia;
}

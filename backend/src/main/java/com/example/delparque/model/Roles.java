package com.example.delparque.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    @Id
    private String id;

    private Boolean administrador;

    private Boolean vigilante;

    private Boolean residente;
}

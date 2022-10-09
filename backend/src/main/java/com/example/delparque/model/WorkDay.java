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
@Table(name = "work_days")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkDay {
    @Id
    private String id;

    @Column(name = "day_name")
    private String dayName;

    @Column(name = "trabajador_id")
    private String trabajadorId;
}
package com.example.delparque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trabajador {
    private String id;
    private String type;
    private String name;
    private String condominoId;
    private String condominoName;
    private String schedule;
    private String telephoneNumber;
    private List<WorkDay> workDays;
}
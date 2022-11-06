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
    private CondominoInfo condominoInfo;
    private String schedule;
    private String telephoneNumber;
    private List<String> workDays;
}
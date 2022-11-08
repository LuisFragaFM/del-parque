package com.example.delparque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CondominoInfo {
    private String userId;
    private String houseNumber;
    private String houseStreet;
    private String owner;
}
package com.example.delparque.service.mappers;

import com.example.delparque.dto.WorkDay;

public class WorkDayMapper {

    public static WorkDay entityToDto(com.example.delparque.model.WorkDay workDay) {
        return WorkDay.builder()
                .id(workDay.getId())
                .dayName(workDay.getDayName())
                .build();
    }

    public static com.example.delparque.model.WorkDay dtoToEntity(WorkDay workDay) {
        return com.example.delparque.model.WorkDay.builder()
                .id(workDay.getId())
                .dayName(workDay.getDayName())
                .build();
    }
}

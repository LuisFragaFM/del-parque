package com.example.delparque.repository;

import com.example.delparque.model.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkDaysRepository extends JpaRepository<WorkDay, String> {
    @Query("SELECT wd.dayName FROM WorkDay wd WHERE wd.trabajadorId = :trabajadorId")
    List<String> findWorkDays(String trabajadorId);
}
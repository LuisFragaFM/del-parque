package com.example.delparque.repository;

import com.example.delparque.model.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkDaysRepository extends JpaRepository<WorkDay, String> {
    List<WorkDay> findAllByTrabajadorId(String trabajadorId);
}
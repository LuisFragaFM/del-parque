package com.example.delparque.repository;

import com.example.delparque.model.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkDaysRepository extends JpaRepository<WorkDay, String> {
    List<WorkDay> findAllByTrabajadorId(String trabajadorId);

    void deleteAllByTrabajadorId(String trabajadorId);
}
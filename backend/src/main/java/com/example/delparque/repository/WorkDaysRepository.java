package com.example.delparque.repository;

import com.example.delparque.model.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkDaysRepository extends JpaRepository<WorkDay, String> {
}

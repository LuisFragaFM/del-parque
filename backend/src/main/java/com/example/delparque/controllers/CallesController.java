package com.example.delparque.controllers;

import com.example.delparque.dto.Calle;
import com.example.delparque.service.CallesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/calles")
public class CallesController {

    private final CallesService callesService;

    public CallesController(CallesService callesService) {
        this.callesService = callesService;
    }

    @GetMapping()
    public List<Calle> findAll() {
        return callesService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Calle> findById(@PathVariable String id) {
        return callesService.findById(id);
    }
}

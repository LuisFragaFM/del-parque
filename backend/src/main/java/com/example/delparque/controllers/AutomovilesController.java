package com.example.delparque.controllers;

import com.example.delparque.service.AutomovilesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/automoviles")
public class AutomovilesController {

    private final AutomovilesService automovilesService;

    public AutomovilesController(AutomovilesService automovilesService) {
        this.automovilesService = automovilesService;
    }

    @GetMapping("/{automovilId}")
    public void findById(@PathVariable String automovilId) {
//        return automovilesService.findById(automovilId);
    }

}

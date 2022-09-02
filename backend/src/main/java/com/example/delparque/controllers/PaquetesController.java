package com.example.delparque.controllers;

import com.example.delparque.dto.Paquete;
import com.example.delparque.service.PaquetesService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/paquetes")
public class PaquetesController {

    private final PaquetesService paquetesService;

    PaquetesController(PaquetesService paquetesService) {
        this.paquetesService = paquetesService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public Page<Paquete> findAll(@RequestParam Integer page) {
        return paquetesService.findAll(page);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public Paquete findById(@PathVariable String id) {
        return paquetesService.findById(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public Paquete save(@RequestBody Paquete paquete) {
        return paquetesService.save(paquete);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public void delete(@PathVariable String id) {
        paquetesService.delete(id);
    }



}


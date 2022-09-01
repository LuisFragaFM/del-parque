package com.example.delparque.controllers;

import com.example.delparque.dto.Visitante;
import com.example.delparque.service.VisitantesService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/visitantes")
public class VisitantesController {

    private final VisitantesService visitantesService;

    VisitantesController(VisitantesService visitantesService) {
        this.visitantesService = visitantesService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public Page<Visitante> findAll(@RequestParam Integer page) {
        return visitantesService.findAll(page);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public Visitante findById(@PathVariable String id) {
        return visitantesService.findById(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_RESIDENT', 'ROLE_ADMIN')")
    public Visitante save(@RequestBody Visitante visitante) {
        return visitantesService.save(visitante);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_RESIDENT', 'ROLE_ADMIN')")
    public void delete(@PathVariable String id) {
        visitantesService.delete(id);
    }

}

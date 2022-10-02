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

    @GetMapping("/unAuthorized")
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public Page<Visitante> findAllUnauthorized(@RequestParam Integer page) {
        return visitantesService.findAllUnauthorized(page);
    }
    @GetMapping("/noLeft")
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public Page<Visitante> findAllNoLeft(@RequestParam Integer page) {
        return visitantesService.findAllNoLeft(page);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public Visitante findById(@PathVariable String id) {
        return visitantesService.findById(id);
    }

    @GetMapping("{name}")
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public Visitante findByName(@PathVariable String name) {
        return visitantesService.findByName(name);
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

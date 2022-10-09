package com.example.delparque.controllers;

import com.example.delparque.dto.Visitante;
import com.example.delparque.service.VisitantesService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/visitantes")
public class VisitantesController {

    private final VisitantesService visitantesService;

    VisitantesController(VisitantesService visitantesService) {
        this.visitantesService = visitantesService;
    }

    @GetMapping("/un-authorized")
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public Page<Visitante> findAllByAuthorized(@RequestParam Integer page) {
        return visitantesService.findAllByAuthorized(page);
    }
    @GetMapping("/gone-out")
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public Page<Visitante> findAllByAuthorizedAndGoneOut(@RequestParam Integer page) {
        return visitantesService.findAllByAuthorizedAndGoneOut(page);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public Visitante findById(@PathVariable String id) {
        return visitantesService.findById(id);
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public List<Visitante> findByName(@PathVariable String name) {
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

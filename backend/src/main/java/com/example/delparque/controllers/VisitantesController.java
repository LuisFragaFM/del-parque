package com.example.delparque.controllers;

import com.example.delparque.dto.Visitante;
import com.example.delparque.service.VisitantesService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/visitantes")
public class VisitantesController {

    private final VisitantesService visitantesService;
    private final SessionHelper sessionHelper;

    VisitantesController(VisitantesService visitantesService, SessionHelper sessionHelper) {
        this.visitantesService = visitantesService;
        this.sessionHelper = sessionHelper;
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUARD')")
    public List<Visitante> findAllByUserId(@RequestParam String userId) {
        return visitantesService.findAllByUserId(userId);
    }

    @GetMapping("/un-authorized/condomino")
    @PreAuthorize("hasAnyRole('ROLE_RESIDENT')")
    public Page<Visitante> findAllByAuthorizedCondomino(@RequestParam Integer page, Principal principal) {
        String userId = sessionHelper.getUserIdForLoggedUser(principal);

        return visitantesService.findAllByAuthorized(page, userId);
    }

    @GetMapping("/un-authorized/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUARD')")
    public Page<Visitante> findAllByAuthorized(@RequestParam Integer page) {
        return visitantesService.findAllByAuthorized(page);
    }

    @GetMapping("/gone-out")
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public Page<Visitante> findAllByGoneOut(@RequestParam Integer page) {
        return visitantesService.findAllByGoneOut(page);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN', 'ROLE_RESIDENT')")
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
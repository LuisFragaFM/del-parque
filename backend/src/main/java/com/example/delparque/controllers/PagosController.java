package com.example.delparque.controllers;

import com.example.delparque.dto.Pago;
import com.example.delparque.service.PagosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagosController {

    private final PagosService pagosService;

    public PagosController(PagosService pagosService) {
        this.pagosService = pagosService;
    }

    @GetMapping()
    public List<Pago> findAll() {
        return pagosService.findAll();
    }

    @PostMapping()
    public Pago save(@RequestBody Pago pago) {
        return pagosService.save(pago);
    }
}

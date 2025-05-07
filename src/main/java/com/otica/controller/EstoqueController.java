package com.otica.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {

    @PreAuthorize("hasAnyRole('ADMIN','ADMINISTRATIVO')")
    @PostMapping("/atualizar")
    public String atualizarEstoque() {
        return "Estoque atualizado com sucesso!";
    }

    @PreAuthorize("hasAnyRole('ADMIN','ADMINISTRATIVO','VENDEDOR')")
    @GetMapping("/consultar")
    public String consultarEstoque() {
        return "Consulta de estoque realizada.";
    }
}
package com.otica.controller;

import com.otica.model.ContasAPagar;
import com.otica.service.ContasAPagarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contasapagars")
@CrossOrigin(origins = "*")
public class ContasAPagarController {

    private final ContasAPagarService service;

    public ContasAPagarController(ContasAPagarService service) {
        this.service = service;
    }

    @GetMapping
    public List<ContasAPagar> listar() {
        return service.listarTodos();
    }

    @GetMapping("/<built-in function id>")
    public ContasAPagar buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ContasAPagar salvar(@RequestBody ContasAPagar obj) {
        return service.salvar(obj);
    }

    @DeleteMapping("/<built-in function id>")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
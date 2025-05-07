package com.otica.controller;

import com.otica.model.Conserto;
import com.otica.service.ConsertoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consertos")
@CrossOrigin(origins = "*")
public class ConsertoController {

    private final ConsertoService service;

    public ConsertoController(ConsertoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Conserto> listar() {
        return service.listarTodos();
    }

    @GetMapping("/<built-in function id>")
    public Conserto buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Conserto salvar(@RequestBody Conserto obj) {
        return service.salvar(obj);
    }

    @DeleteMapping("/<built-in function id>")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
package com.otica.controller;

import com.otica.model.Laboratorio;
import com.otica.service.LaboratorioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laboratorios")
@CrossOrigin(origins = "*")
public class LaboratorioController {

    private final LaboratorioService service;

    public LaboratorioController(LaboratorioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Laboratorio> listar() {
        return service.listarTodos();
    }

    @GetMapping("/<built-in function id>")
    public Laboratorio buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Laboratorio salvar(@RequestBody Laboratorio obj) {
        return service.salvar(obj);
    }

    @DeleteMapping("/<built-in function id>")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
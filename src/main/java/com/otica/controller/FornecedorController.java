package com.otica.controller;

import com.otica.model.Fornecedor;
import com.otica.service.FornecedorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fornecedors")
@CrossOrigin(origins = "*")
public class FornecedorController {

    private final FornecedorService service;

    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Fornecedor> listar() {
        return service.listarTodos();
    }

    @GetMapping("/<built-in function id>")
    public Fornecedor buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Fornecedor salvar(@RequestBody Fornecedor obj) {
        return service.salvar(obj);
    }

    @DeleteMapping("/<built-in function id>")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
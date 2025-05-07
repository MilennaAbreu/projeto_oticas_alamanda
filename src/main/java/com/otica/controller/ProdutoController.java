package com.otica.controller;

import com.otica.model.Produto;
import com.otica.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Produto> listar() {
        return service.listarTodos();
    }

    @GetMapping("/<built-in function id>")
    public Produto buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto obj) {
        return service.salvar(obj);
    }

    @DeleteMapping("/<built-in function id>")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
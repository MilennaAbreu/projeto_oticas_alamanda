package com.otica.controller;

import com.otica.model.CondicaoPagamento;
import com.otica.service.CondicaoPagamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/condicaopagamentos")
@CrossOrigin(origins = "*")
public class CondicaoPagamentoController {

    private final CondicaoPagamentoService service;

    public CondicaoPagamentoController(CondicaoPagamentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<CondicaoPagamento> listar() {
        return service.listarTodos();
    }

    @GetMapping("/<built-in function id>")
    public CondicaoPagamento buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public CondicaoPagamento salvar(@RequestBody CondicaoPagamento obj) {
        return service.salvar(obj);
    }

    @DeleteMapping("/<built-in function id>")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
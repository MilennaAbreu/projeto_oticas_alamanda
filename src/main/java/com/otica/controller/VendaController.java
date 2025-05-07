package com.otica.controller;

import com.otica.model.Venda;
import com.otica.service.VendaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
@CrossOrigin(origins = "*")
public class VendaController {

    private final VendaService service;

    public VendaController(VendaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Venda> listar() {
        return service.listarTodos();
    }

    @GetMapping("/<built-in function id>")
    public Venda buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Venda salvar(@RequestBody Venda obj) {
        return service.salvar(obj);
    }

    @DeleteMapping("/<built-in function id>")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
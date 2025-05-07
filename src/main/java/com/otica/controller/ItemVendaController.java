package com.otica.controller;

import com.otica.model.ItemVenda;
import com.otica.service.ItemVendaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itemvendas")
@CrossOrigin(origins = "*")
public class ItemVendaController {

    private final ItemVendaService service;

    public ItemVendaController(ItemVendaService service) {
        this.service = service;
    }

    @GetMapping
    public List<ItemVenda> listar() {
        return service.listarTodos();
    }

    @GetMapping("/<built-in function id>")
    public ItemVenda buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ItemVenda salvar(@RequestBody ItemVenda obj) {
        return service.salvar(obj);
    }

    @DeleteMapping("/<built-in function id>")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
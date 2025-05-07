package com.otica.controller;

import com.otica.model.ContasAReceber;
import com.otica.service.ContasAReceberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contasarecebers")
@CrossOrigin(origins = "*")
public class ContasAReceberController {

    private final ContasAReceberService service;

    public ContasAReceberController(ContasAReceberService service) {
        this.service = service;
    }

    @GetMapping
    public List<ContasAReceber> listar() {
        return service.listarTodos();
    }

    @GetMapping("/<built-in function id>")
    public ContasAReceber buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ContasAReceber salvar(@RequestBody ContasAReceber obj) {
        return service.salvar(obj);
    }

    @DeleteMapping("/<built-in function id>")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
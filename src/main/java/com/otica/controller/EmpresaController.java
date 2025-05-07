package com.otica.controller;

import com.otica.model.Empresa;
import com.otica.service.EmpresaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*")
public class EmpresaController {

    private final EmpresaService service;

    public EmpresaController(EmpresaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Empresa> listar() {
        return service.listarTodos();
    }

    @GetMapping("/<built-in function id>")
    public Empresa buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Empresa salvar(@RequestBody Empresa obj) {
        return service.salvar(obj);
    }

    @DeleteMapping("/<built-in function id>")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
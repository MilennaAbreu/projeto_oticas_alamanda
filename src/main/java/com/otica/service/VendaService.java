package com.otica.service;

import com.otica.model.Venda;
import com.otica.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    private final VendaRepository repository;

    public VendaService(VendaRepository repository) {
        this.repository = repository;
    }

    public List<Venda> listarTodos() {
        return repository.findAll();
    }

    public Venda buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Venda salvar(Venda obj) {
        return repository.save(obj);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
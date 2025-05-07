package com.otica.service;

import com.otica.model.Conserto;
import com.otica.repository.ConsertoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsertoService {

    private final ConsertoRepository repository;

    public ConsertoService(ConsertoRepository repository) {
        this.repository = repository;
    }

    public List<Conserto> listarTodos() {
        return repository.findAll();
    }

    public Conserto buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Conserto salvar(Conserto obj) {
        return repository.save(obj);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
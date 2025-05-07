package com.otica.service;

import com.otica.model.ContasAPagar;
import com.otica.repository.ContasAPagarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContasAPagarService {

    private final ContasAPagarRepository repository;

    public ContasAPagarService(ContasAPagarRepository repository) {
        this.repository = repository;
    }

    public List<ContasAPagar> listarTodos() {
        return repository.findAll();
    }

    public ContasAPagar buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ContasAPagar salvar(ContasAPagar obj) {
        return repository.save(obj);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
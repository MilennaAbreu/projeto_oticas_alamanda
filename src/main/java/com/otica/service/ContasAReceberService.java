package com.otica.service;

import com.otica.model.ContasAReceber;
import com.otica.repository.ContasAReceberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContasAReceberService {

    private final ContasAReceberRepository repository;

    public ContasAReceberService(ContasAReceberRepository repository) {
        this.repository = repository;
    }

    public List<ContasAReceber> listarTodos() {
        return repository.findAll();
    }

    public ContasAReceber buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ContasAReceber salvar(ContasAReceber obj) {
        return repository.save(obj);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
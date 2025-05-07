package com.otica.service;

import com.otica.model.CondicaoPagamento;
import com.otica.repository.CondicaoPagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CondicaoPagamentoService {

    private final CondicaoPagamentoRepository repository;

    public CondicaoPagamentoService(CondicaoPagamentoRepository repository) {
        this.repository = repository;
    }

    public List<CondicaoPagamento> listarTodos() {
        return repository.findAll();
    }

    public CondicaoPagamento buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public CondicaoPagamento salvar(CondicaoPagamento obj) {
        return repository.save(obj);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
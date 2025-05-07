package com.otica.service;

import com.otica.model.Produto;
import com.otica.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Produto salvar(Produto obj) {
        return repository.save(obj);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
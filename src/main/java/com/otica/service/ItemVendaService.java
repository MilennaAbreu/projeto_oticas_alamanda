package com.otica.service;

import com.otica.model.ItemVenda;
import com.otica.repository.ItemVendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemVendaService {

    private final ItemVendaRepository repository;

    public ItemVendaService(ItemVendaRepository repository) {
        this.repository = repository;
    }

    public List<ItemVenda> listarTodos() {
        return repository.findAll();
    }

    public ItemVenda buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ItemVenda salvar(ItemVenda obj) {
        return repository.save(obj);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
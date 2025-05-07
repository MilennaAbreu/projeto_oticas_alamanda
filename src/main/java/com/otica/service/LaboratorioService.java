package com.otica.service;

import com.otica.model.Laboratorio;
import com.otica.repository.LaboratorioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratorioService {

    private final LaboratorioRepository repository;

    public LaboratorioService(LaboratorioRepository repository) {
        this.repository = repository;
    }

    public List<Laboratorio> listarTodos() {
        return repository.findAll();
    }

    public Laboratorio buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Laboratorio salvar(Laboratorio obj) {
        return repository.save(obj);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
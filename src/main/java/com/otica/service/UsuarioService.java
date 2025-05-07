package com.otica.service;

import com.otica.model.Usuario;
import com.otica.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Usuario salvar(Usuario obj) {
        return repository.save(obj);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
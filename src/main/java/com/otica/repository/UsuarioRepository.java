package com.otica.repository;

import com.otica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional; // adicione este import

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // método de busca por login
    Optional<Usuario> findByLogin(String login);
}

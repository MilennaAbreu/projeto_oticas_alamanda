package com.otica.controller;

import com.otica.model.Usuario;
import com.otica.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PreAuthorize("hasAnyRole('ADMIN','DIRETORIA','ADMINISTRATIVO')")
    @GetMapping
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN','DIRETORIA','ADMINISTRATIVO')")
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Usuario usuario,
                                    @AuthenticationPrincipal UserDetails userDetails) {

        Usuario atual = usuarioRepository.findByLogin(userDetails.getUsername()).orElseThrow();

        if ("DIRETORIA".equals(atual.getPerfil().name()) && "ADMIN".equals(usuario.getPerfil().name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("erro", "DIRETORIA não pode criar usuários ADMIN"));
        }

        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

    @PreAuthorize("hasAnyRole('ADMIN','DIRETORIA','ADMINISTRATIVO')")
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                       @RequestBody Usuario usuarioAtualizado,
                                       @AuthenticationPrincipal UserDetails userDetails) {

        Usuario usuarioExistente = usuarioRepository.findById(id).orElseThrow();
        Usuario atual = usuarioRepository.findByLogin(userDetails.getUsername()).orElseThrow();

        // DIRETORIA não pode alterar perfil para ADMIN
        if ("DIRETORIA".equals(atual.getPerfil().name()) &&
            "ADMIN".equals(usuarioAtualizado.getPerfil().name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("erro", "DIRETORIA não pode atribuir perfil ADMIN"));
        }

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setLogin(usuarioAtualizado.getLogin());

        if (!"ADMINISTRATIVO".equals(atual.getPerfil().name())) {
            usuarioExistente.setPerfil(usuarioAtualizado.getPerfil());
        }

        return ResponseEntity.ok(usuarioRepository.save(usuarioExistente));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}
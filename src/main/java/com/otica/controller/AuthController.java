package com.otica.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.otica.security.JwtUtil;
import com.otica.model.Usuario;
import com.otica.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(JwtUtil jwtUtil, UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> user) {
        String login = user.get("login");
        String senha = user.get("senha");

        Optional<Usuario> optUsuario = usuarioRepository.findByLogin(login);

        if (optUsuario.isEmpty() || !passwordEncoder.matches(senha, optUsuario.get().getSenha())) {
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Login inválido");
            return ResponseEntity.badRequest().body(erro);
        }

        String accessToken = jwtUtil.generateToken(login);
        String refreshToken = jwtUtil.generateRefreshToken(login);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> body) {
        String refreshToken = body.get("refresh_token");
        if (refreshToken == null || !jwtUtil.validateRefreshToken(refreshToken)) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Refresh token inválido"));
        }

        String username = jwtUtil.extractUsername(refreshToken);
        String newAccessToken = jwtUtil.generateToken(username);

        return ResponseEntity.ok(Map.of("access_token", newAccessToken));
    }
}
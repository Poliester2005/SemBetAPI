package br.com.fiap.SemBet.controller;

import br.com.fiap.SemBet.dto.LoginRequest;
import br.com.fiap.SemBet.dto.LoginResponse;
import br.com.fiap.SemBet.repository.UsuarioRepository;
import br.com.fiap.SemBet.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository repository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioRepository repository, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {
        var usuario = repository.findByEmail(login.getEmail());

        if (usuario.isPresent() && passwordEncoder.matches(login.getSenha(), usuario.get().getSenha())) {
            String token = tokenService.gerarToken(usuario.get().getEmail());
            return ResponseEntity.ok(new LoginResponse(token));
        }

        return ResponseEntity.status(401).body("Usuário ou senha inválidos");
    }
}

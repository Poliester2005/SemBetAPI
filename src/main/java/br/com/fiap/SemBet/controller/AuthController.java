package br.com.fiap.SemBet.controller;

import br.com.fiap.SemBet.dto.LoginRequest;
import br.com.fiap.SemBet.dto.LoginResponse;
import br.com.fiap.SemBet.repository.UsuarioRepository;
import br.com.fiap.SemBet.security.TokenService;
import br.com.fiap.SemBet.service.SmsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository repository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final SmsService smsService;

    // Cache simples para OTPs
    private final Map<String, String> otpCache = new ConcurrentHashMap<>();
    private final SecureRandom random = new SecureRandom();

    public AuthController(UsuarioRepository repository, TokenService tokenService,
                          PasswordEncoder passwordEncoder, SmsService smsService) {
        this.repository = repository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.smsService = smsService;
    }

    // Passo 1: Login email + senha -> gerar e enviar OTP SMS
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {
        Optional<?> usuarioOpt = repository.findByEmail(login.getEmail());

        if (usuarioOpt.isPresent()) {
            var usuario = usuarioOpt.get();

            // Valida senha
            if (passwordEncoder.matches(login.getSenha(), ((br.com.fiap.SemBet.model.Usuario) usuario).getSenha())) {

                // Gera OTP de 6 dígitos
                String otp = String.format("%06d", random.nextInt(1_000_000));
                otpCache.put(((br.com.fiap.SemBet.model.Usuario) usuario).getEmail(), otp);

                // Envia SMS
                String telefone = ((br.com.fiap.SemBet.model.Usuario) usuario).getTelefone();
                smsService.sendSms(telefone, "Seu código de verificação é: " + otp);

                return ResponseEntity.ok("Código OTP enviado via SMS");
            }
        }

        return ResponseEntity.status(401).body("Usuário ou senha inválidos");
    }

    // Passo 2: Validar OTP e gerar token JWT
    @PostMapping("/validate-otp")
    public ResponseEntity<?> validateOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");

        if (email == null || otp == null) {
            return ResponseEntity.badRequest().body("Email e OTP são obrigatórios");
        }

        String cachedOtp = otpCache.get(email);

        if (cachedOtp != null && cachedOtp.equals(otp)) {
            otpCache.remove(email);
            String token = tokenService.gerarToken(email);
            return ResponseEntity.ok(new LoginResponse(token));
        } else {
            return ResponseEntity.status(401).body("Código OTP inválido");
        }
    }
}

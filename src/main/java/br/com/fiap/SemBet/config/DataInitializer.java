package br.com.fiap.SemBet.config;

import br.com.fiap.SemBet.enums.TipoAcaoSuspeita;
import br.com.fiap.SemBet.model.Acesso;
import br.com.fiap.SemBet.model.Usuario;
import br.com.fiap.SemBet.repository.AcessoRepository;
import br.com.fiap.SemBet.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepo, AcessoRepository acessoRepo) {
        return args -> {
            // Criador de senhas criptografadas
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            // Criar usuário com senha criptografada
            Usuario usuario = new Usuario();
            usuario.setNome("Eduardo Sanchez");
            usuario.setEmail("eduardo@sembet.com");
            usuario.setSenha(encoder.encode("senha123")); // Senha criptografada com BCrypt
            usuario.setPerfil("ADMIN");
            usuario.setTelefone("11999999999");

            usuarioRepo.save(usuario);

            // Criar acessos suspeitos para o usuário
            Acesso acesso1 = new Acesso();
            acesso1.setUsuario(usuario);
            acesso1.setIp("192.168.0.1");
            acesso1.setAcao(TipoAcaoSuspeita.valueOf(TipoAcaoSuspeita.TENTATIVA_ACESSO_SITE_APOSTA.name()));

            Acesso acesso2 = new Acesso();
            acesso2.setUsuario(usuario);
            acesso2.setIp("10.0.0.5");
            acesso2.setAcao(TipoAcaoSuspeita.valueOf(TipoAcaoSuspeita.IGNORAR_RECOMENDACOES.name()));

            acessoRepo.saveAll(List.of(acesso1, acesso2));
        };
    }
}

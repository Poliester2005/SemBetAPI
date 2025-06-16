package br.com.fiap.SemBet;

import br.com.fiap.SemBet.model.Usuario;
import br.com.fiap.SemBet.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class SemBetApplication {

    public static void main(String[] args) {
        SpringApplication.run(SemBetApplication.class, args);
    }


    @Bean
    public CommandLineRunner initDatabase(UsuarioRepository usuarioRepository) {
        return args -> {
            usuarioRepository.save(new Usuario(
                    "Maria",
                    "maria@email.com",
                    "senha789",
                    "USER",
                    Collections.emptyList()  // sem acessos iniciais
            ));

            usuarioRepository.save(new Usuario(
                    "João",
                    "joao@email.com",
                    "senha123",
                    "ADMIN",
                    Collections.emptyList()
            ));

            usuarioRepository.save(new Usuario(
                    "Ana",
                    "ana@email.com",
                    "senha456",
                    "USER",
                    Collections.emptyList()
            ));
        };
    }
}

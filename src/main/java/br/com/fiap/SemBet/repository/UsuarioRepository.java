package br.com.fiap.SemBet.repository;

import br.com.fiap.SemBet.model.Usuario;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findById(Long id);

    List<Usuario> findAll();

    Usuario save(Usuario usuario);

    void deleteById(Long id);

    Optional<Usuario> findByEmail(String email);
}

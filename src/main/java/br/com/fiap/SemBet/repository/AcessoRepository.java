package br.com.fiap.SemBet.repository;

import br.com.fiap.SemBet.model.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Long> {
    // Método para filtrar por tipo de ação (campo acao)
    List<Acesso> findByAcaoContainingIgnoreCase(String acao);
}

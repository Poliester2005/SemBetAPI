package br.com.fiap.SemBet.repository;

import br.com.fiap.SemBet.enums.TipoAcaoSuspeita;
import br.com.fiap.SemBet.model.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Long> {
    List<Acesso> findByAcao(TipoAcaoSuspeita acao);
}

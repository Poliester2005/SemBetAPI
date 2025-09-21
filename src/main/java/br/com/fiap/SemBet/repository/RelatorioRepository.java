package br.com.fiap.SemBet.repository;

import br.com.fiap.SemBet.enums.TipoAcaoSuspeita;
import br.com.fiap.SemBet.model.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatorioRepository extends JpaRepository<Acesso, Long> {

    @Query("SELECT a.acao AS tipo, COUNT(a) AS total " +
            "FROM Acesso a " +
            "WHERE a.usuario.id = :usuarioId " +
            "GROUP BY a.acao")
    List<TipoCount> contarPorTipo(@Param("usuarioId") Long usuarioId);

    @Query("SELECT COUNT(a) FROM Acesso a WHERE a.usuario.id = :usuarioId")
    Long contarTotalPorUsuario(@Param("usuarioId") Long usuarioId);

    interface TipoCount {
        TipoAcaoSuspeita getTipo();
        Long getTotal();
    }

    @Query("SELECT CAST(a.horario AS date) AS data, COUNT(a) AS total " +
            "FROM Acesso a " +
            "WHERE a.usuario.id = :usuarioId AND a.acao IN :acoesSuspeitas " +
            "GROUP BY CAST(a.horario AS date) " +
            "ORDER BY data")
    List<DataCount> contarAcessosSuspeitosPorDia(
            @Param("usuarioId") Long usuarioId,
            @Param("acoesSuspeitas") List<TipoAcaoSuspeita> acoesSuspeitas);


    interface DataCount {
        java.sql.Date getData();
        Long getTotal();
    }

}

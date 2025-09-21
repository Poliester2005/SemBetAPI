package br.com.fiap.SemBet.service;

import br.com.fiap.SemBet.enums.TipoAcaoSuspeita;
import br.com.fiap.SemBet.repository.RelatorioRepository;
import br.com.fiap.SemBet.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RelatoriosService {

    @Autowired
    private RelatorioRepository relatorioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Double> calcularPorcentagensPorUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new EntityNotFoundException("Usuário não encontrado com id " + usuarioId);
        }

        Long total = relatorioRepository.contarTotalPorUsuario(usuarioId);

        if (total == null || total == 0) {
            return Arrays.stream(TipoAcaoSuspeita.values())
                    .collect(Collectors.toMap(Enum::name, tipo -> 0.0));
        }

        List<RelatorioRepository.TipoCount> contagens = relatorioRepository.contarPorTipo(usuarioId);

        Map<String, Double> porcentagens = Arrays.stream(TipoAcaoSuspeita.values())
                .collect(Collectors.toMap(
                        Enum::name,
                        tipo -> 0.0
                ));

        for (RelatorioRepository.TipoCount tc : contagens) {
            double perc = (tc.getTotal() * 100.0) / total;
            porcentagens.put(tc.getTipo().name(), perc);
        }

        return porcentagens;
    }

    public Map<java.sql.Date, Long> gerarRelatorioAcessosSuspeitosPorDia(Long usuarioId) {
        List<TipoAcaoSuspeita> tiposSuspeitos = Arrays.asList(TipoAcaoSuspeita.values());

        List<RelatorioRepository.DataCount> dados = relatorioRepository.contarAcessosSuspeitosPorDia(usuarioId, tiposSuspeitos);

        // Pode retornar direto a lista, ou converter para Map<Date, Long>
        return dados.stream()
                .collect(Collectors.toMap(RelatorioRepository.DataCount::getData, RelatorioRepository.DataCount::getTotal));
    }

}

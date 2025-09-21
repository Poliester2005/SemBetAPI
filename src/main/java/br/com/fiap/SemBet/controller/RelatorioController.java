package br.com.fiap.SemBet.controller;

import br.com.fiap.SemBet.service.AcessoService;
import br.com.fiap.SemBet.service.RelatoriosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/relatorios/v1")
public class RelatorioController {

    @Autowired
    private RelatoriosService service;

    @GetMapping("/porcentagem/{usuarioId}")
    public ResponseEntity<Map<String, Double>> obterPorcentagemAcessosSuspeitos(
            @PathVariable Long usuarioId) {
        Map<String, Double> porcentagens = service.calcularPorcentagensPorUsuario(usuarioId);
        return ResponseEntity.ok(porcentagens);
    }

    @GetMapping("/periodo/{usuarioId}")
    public ResponseEntity<Map<java.sql.Date, Long>> obterRelatorioAcessosSuspeitosPorPeriodo(
            @PathVariable Long usuarioId) {
        Map<java.sql.Date, Long> relatorio = service.gerarRelatorioAcessosSuspeitosPorDia(usuarioId);
        return ResponseEntity.ok(relatorio);
    }

}

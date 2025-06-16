package br.com.fiap.SemBet.controller;

import br.com.fiap.SemBet.model.Acesso;
import br.com.fiap.SemBet.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acessos/v1")
public class AcessoController {

    @Autowired
    private AcessoService service;

    @GetMapping
    public List<Acesso> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Acesso> buscarPorId(@PathVariable Long id) {
        Optional<Acesso> acesso = Optional.ofNullable(service.buscarPorId(id));
        return acesso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/acao")
    public List<Acesso> buscarPorAcao(@RequestParam String acao) {
        return service.buscarPorAcao(acao);
    }

    @PostMapping
    public Acesso criarAcesso(@RequestBody Acesso acesso) {
        return service.salvar(acesso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAcesso(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

package br.com.fiap.SemBet.controller;

import br.com.fiap.SemBet.dto.MensagemDTO;
import br.com.fiap.SemBet.enums.TipoAcaoSuspeita;
import br.com.fiap.SemBet.model.Acesso;
import br.com.fiap.SemBet.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Acesso> acesso = service.buscarPorId(id);

        if (acesso.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensagemDTO("Acesso não encontrado com id: " + id));
        }

        return ResponseEntity.ok(acesso.get());
    }


    @GetMapping("/acao")
    public ResponseEntity<?> buscarPorAcao(@RequestParam TipoAcaoSuspeita acao) {
        List<Acesso> acessos = service.buscarPorAcao(acao);

        if (acessos.isEmpty()) {
            String mensagem = "Nenhum acesso encontrado para a ação suspeita: " + acao.name();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MensagemDTO("Nenhum acesso encontrado para a ação: " + acao.name()));
        }

        return ResponseEntity.ok(acessos);
    }


    @PostMapping
    public Acesso criarAcesso(@RequestBody Acesso acesso) {
        return service.salvar(acesso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        boolean sucesso = service.deletar(id);

        if (!sucesso) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MensagemDTO("Acesso não encontrado com id: " + id));
        }

        return ResponseEntity.noContent().build();
    }


}

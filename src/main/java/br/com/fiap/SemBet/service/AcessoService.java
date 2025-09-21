package br.com.fiap.SemBet.service;

import br.com.fiap.SemBet.enums.TipoAcaoSuspeita;
import br.com.fiap.SemBet.exception.ResourceNotFoundException;
import br.com.fiap.SemBet.model.Acesso;
import br.com.fiap.SemBet.repository.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AcessoService {

    @Autowired
    private AcessoRepository repository;

    public List<Acesso> listarTodos() {
        return repository.findAll();
    }

    public Optional<Acesso> buscarPorId(Long id) {
        return repository.findById(id);
    }


    public List<Acesso> buscarPorAcao(TipoAcaoSuspeita acao) {
        return repository.findByAcao(acao);
    }

    public Acesso salvar(Acesso acesso) {
        acesso.setHorario(LocalDateTime.now());
        return repository.save(acesso);
    }

    public boolean deletar(Long id) {
        Optional<Acesso> acesso = buscarPorId(id);

        if (acesso.isEmpty()) {
            return false; // sinaliza que não achou
        }

        repository.delete(acesso.get());
        return true;
    }


}

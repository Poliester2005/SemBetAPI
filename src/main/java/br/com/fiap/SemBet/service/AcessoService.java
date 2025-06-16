package br.com.fiap.SemBet.service;

import br.com.fiap.SemBet.exception.ResourceNotFoundException;
import br.com.fiap.SemBet.model.Acesso;
import br.com.fiap.SemBet.repository.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AcessoService {

    @Autowired
    private AcessoRepository repository;

    public List<Acesso> listarTodos() {
        return repository.findAll();
    }

    public Acesso buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Acesso não encontrado com id " + id));
    }

    public List<Acesso> buscarPorAcao(String acao) {
        return repository.findByAcaoContainingIgnoreCase(acao);
    }

    public Acesso salvar(Acesso acesso) {
        acesso.setHorario(LocalDateTime.now());
        return repository.save(acesso);
    }

    public void deletar(Long id) {
        Acesso acesso = buscarPorId(id); // garante que existe ou lança exceção
        repository.delete(acesso);
    }
}

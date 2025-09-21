package br.com.fiap.SemBet.model;

import br.com.fiap.SemBet.enums.TipoAcaoSuspeita;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Acesso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference

    private Usuario usuario;

    private String ip;

    @Enumerated(EnumType.STRING)
    private TipoAcaoSuspeita acao;

    @Column(nullable = false)
    private LocalDateTime horario = LocalDateTime.now();

    public Acesso() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public TipoAcaoSuspeita getAcao() {
        return acao;
    }

    public void setAcao(TipoAcaoSuspeita acao) {
        this.acao = acao;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }
}

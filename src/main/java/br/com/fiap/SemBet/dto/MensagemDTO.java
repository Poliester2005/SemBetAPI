package br.com.fiap.SemBet.dto;

public class MensagemDTO {
    private String mensagem;

    public MensagemDTO(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}

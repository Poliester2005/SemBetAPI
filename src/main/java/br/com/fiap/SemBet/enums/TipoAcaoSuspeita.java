package br.com.fiap.SemBet.enums;

public enum TipoAcaoSuspeita {

    TENTATIVA_ACESSO_SITE_APOSTA("Tentativa de acessar sites de apostas"),
    DESATIVACAO_ALERTAS("Tentativa de desativar notificações ou alertas preventivos"),
    IGNORAR_RECOMENDACOES("Ignorar sistematicamente as recomendações e alertas do app");

    private final String descricao;

    TipoAcaoSuspeita(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

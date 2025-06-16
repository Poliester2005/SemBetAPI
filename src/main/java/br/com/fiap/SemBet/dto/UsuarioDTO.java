package br.com.fiap.SemBet.dto;

import jakarta.validation.constraints.*;
import org.apache.commons.text.StringEscapeUtils;

public class UsuarioDTO {

    @NotBlank(message = "Nome não pode ser vazio")
    @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String senha;

    @NotBlank(message = "Perfil é obrigatório")
    @Pattern(regexp = "^(ADMIN|USUARIO)$", message = "Perfil deve ser ADMIN ou USUARIO")
    private String perfil;

    // Getters e Setters com sanitização no setter (opcional)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = StringEscapeUtils.escapeHtml4(nome);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = StringEscapeUtils.escapeHtml4(email);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha; // A senha pode ser criptografada depois
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = StringEscapeUtils.escapeHtml4(perfil);
    }
}

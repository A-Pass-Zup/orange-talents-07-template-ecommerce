package br.com.zupacademy.apass.mercadolivre.dto.request;

import br.com.zupacademy.apass.mercadolivre.model.Usuario;
import br.com.zupacademy.apass.mercadolivre.validation.constraints.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoUsuarioRequest {

    @NotBlank
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "login")
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;

    /**
     * Construtor com os dados obrigatórios.
     * @param login
     * @param senha
     */
    public NovoUsuarioRequest(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {

        this.login = login;
        this.senha = senha;
    }

    /**
     * Converte o objeto para modelo de domínio/entidade.
     * @return
     */
    public Usuario converte() {
        return new Usuario(this.login, this.senha);
    }
}

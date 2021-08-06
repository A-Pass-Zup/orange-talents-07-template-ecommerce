package br.com.zupacademy.apass.mercadolivre.dto.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutenticacaoRequest {
    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public AutenticacaoRequest(@NotBlank String login, @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken paraAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(this.login, this.senha);
    }
}

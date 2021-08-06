package br.com.zupacademy.apass.mercadolivre.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Armazena o login (e-mail) do usuário.
     */
    @NotBlank
    @Email
    @Column(nullable = false)
    private String login;

    /**
     * Armazena a senha do usuário criptografada.
     */
    @NotBlank
    @Column(nullable = false)
    private String senha;

    /**
     * Armazena a data e hora que o usuário foi registrado.
     */
    @Column(nullable = false)
    private LocalDateTime dataHoraCadastro = LocalDateTime.now();

    /**
     * Construtor padrão com os dados obrigatórios.
     * A senha do usuário será critografada na construção do objeto.
     *
     * @param login O e-mail do usuário.
     * @param senhaNaoCriptografada A senha em text (não criptografada).
     */
    public Usuario (@NotBlank String login, @NotBlank @Size(min = 6) String senhaNaoCriptografada) {

        Assert.hasText(login, "Não pode criar um usuário sem login!");
        Assert.hasLength(senhaNaoCriptografada, "Não pode criar um usuário sem senha!");
        Assert.isTrue(senhaNaoCriptografada.length() >= 6, "A senha precisa ter no mínimo 6 caracteres!");

        this.login = login;
        this.senha = new BCryptPasswordEncoder().encode(senhaNaoCriptografada);
    }

    @Deprecated
    private Usuario() {}

    public String getLogin() {
        return this.login;
    }

    public String getSenha() {
        return this.senha;
    }
}

package br.com.zupacademy.apass.mercadolivre.model.entity;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;

@Entity
public class ProdutoPergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String titulo;

    @NotNull
    @ManyToOne(optional = false)
    private Produto produto;

    @NotNull
    @ManyToOne(optional = false)
    private Usuario usuario;

    @NotNull
    private LocalDateTime dataHoraCadastro = LocalDateTime.now();

    /**
     * Construtor para o JPA. Não use.
     */
    @Deprecated
    protected ProdutoPergunta() {}

    /**
     * Construtor com os dados obrigatórios.
     * @param titulo
     * @param produto
     * @param usuario
     */
    public ProdutoPergunta(@NotBlank String titulo, @NotNull Produto produto, @NotNull Usuario usuario) {

        Assert.hasText(titulo, "Não pode criar uma pergunta de produto sem título!");

        Assert.notNull(produto, "Não pode criar uma pergunta de produto sem produto!");

        Assert.notNull(usuario, "Não pode criar uma pergunta de produto sem usuário!");

        this.titulo = titulo;

        this.produto = produto;
        this.produto.addPergunta(this);

        this.usuario = usuario;
    }

    /**
     * Varifica se a pergunta é do produto passado no parâmentro.
     * @param produto
     * @return
     */
    public boolean sobreOProduto(Produto produto) {
        return this.produto == produto;
    }

    /**
     * Obtém o usuário que fez a pergunta.
     *
     * @return
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Obtém o produto da pergunta.
     *
     * @return
     */
    public Produto getProduto() {
        return this.produto;
    }

    /**
     * Obtém a data e hora do cadastro da pergunta.
     *
     * @return
     */
    public LocalDateTime getDataHoraCadastro() {
        return this.dataHoraCadastro;
    }

    /**
     * Obtém o título da pergunta.
     *
     * @return
     */
    public String getTitulo() {
        return this.titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoPergunta that = (ProdutoPergunta) o;
        return Objects.equals(this.titulo.toLowerCase(), that.titulo.toLowerCase())
                && Objects.equals(produto, that.produto)
                && Objects.equals(usuario, that.usuario)
                && Objects.equals(dataHoraCadastro, that.dataHoraCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo.toLowerCase(), produto, usuario, dataHoraCadastro);
    }
}

package br.com.zupacademy.apass.mercadolivre.model.entity;


import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class ProdutoCaracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String descricao;

    @ManyToOne(optional = false)
    private Produto produto;

    /**
     * Contrutor para a JPA. Não utilizar.
     */
    @Deprecated
    protected ProdutoCaracteristica() {
    }

    /**
     * Construtor com dados obrigatórios.
     * @param nome
     * @param descricao
     * @param produto
     */
    public ProdutoCaracteristica(@NotBlank String nome, @NotBlank String descricao, @NotNull Produto produto) {
        Assert.hasText(nome, "Não pode criar característica sem nome!");
        Assert.hasText(descricao, "Não pode criar característica sem descrição");
        Assert.notNull(produto, "Não pode existir característica que não esteja associada com Produto");

        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoCaracteristica that = (ProdutoCaracteristica) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(descricao, that.descricao) && Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, produto);
    }
}

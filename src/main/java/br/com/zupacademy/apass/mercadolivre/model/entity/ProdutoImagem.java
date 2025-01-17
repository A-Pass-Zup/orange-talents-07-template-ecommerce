package br.com.zupacademy.apass.mercadolivre.model.entity;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class ProdutoImagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Produto produto;

    @Column(nullable = false)
    private String imagem;

    @Deprecated
    protected ProdutoImagem(){
    }

    /**
     * Contrutor com os dados obrigatórios
     * @param produto
     * @param imagem
     */
    public ProdutoImagem(@NotNull Produto produto, @NotBlank String imagem) {
        Assert.notNull(produto, "Não pode adicionar imagem sem definir o produto!");
        Assert.hasText(imagem, "Precisa definir o caminho da imagem do produto!");

        this.produto = produto;
        this.imagem = imagem;
    }

    public String getImagem() {
        return imagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoImagem that = (ProdutoImagem) o;
        return Objects.equals(produto, that.produto) && Objects.equals(imagem, that.imagem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, imagem);
    }
}

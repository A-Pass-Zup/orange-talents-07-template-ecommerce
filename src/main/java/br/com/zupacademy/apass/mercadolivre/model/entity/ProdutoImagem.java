package br.com.zupacademy.apass.mercadolivre.model.entity;

import br.com.zupacademy.apass.mercadolivre.model.entity.Produto;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
}

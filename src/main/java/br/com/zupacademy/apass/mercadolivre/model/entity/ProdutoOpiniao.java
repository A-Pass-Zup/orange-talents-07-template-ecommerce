package br.com.zupacademy.apass.mercadolivre.model.entity;

import br.com.zupacademy.apass.mercadolivre.model.entity.Produto;
import br.com.zupacademy.apass.mercadolivre.model.entity.Usuario;
import io.jsonwebtoken.lang.Assert;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ProdutoOpiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String titulo;

    @NotNull
    @Range(min = 1, max = 5)
    private Integer nota;

    @NotBlank
    @NotNull
    @Size(max = 500)
    private String descricao;

    @ManyToOne(optional = false)
    private Produto produto;

    @ManyToOne(optional = false)
    private Usuario usuarioLogado;

    @Deprecated
    protected ProdutoOpiniao(){
    }

    public ProdutoOpiniao(@NotBlank String titulo,
                          @NotNull Integer nota,
                          @NotBlank String descricao,
                          @NotNull Produto produto,
                          @NotNull Usuario usuarioLogado) {

        Assert.hasText(titulo, "Não pode criar opnião sem título!");

        Assert.notNull(nota, "Não pode criar opnião sem nota!");
        Assert.isTrue(1 <= nota && nota <= 5, "A nota precisa ser entre 1 e 5!");

        Assert.hasText(descricao, "Não pode criar opnião sem descrição");
        Assert.isTrue(descricao.length() <= 500, "A descrição da opnião precisa ter no máxima 500 caracteres!");

        Assert.notNull(produto, "Não pode criar opnião sem um produto vinculado!");

        Assert.notNull(usuarioLogado, "Não pode criar produto sem usuário de vínculo!");

        this.titulo = titulo;
        this.nota = nota;
        this.descricao = descricao;
        this.produto = produto;
        this.usuarioLogado = usuarioLogado;
    }

    public Boolean pertenceAoProduto(Produto produto) {
        return produto == this.produto;
    }
}

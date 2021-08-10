package br.com.zupacademy.apass.mercadolivre.dto.request;

import br.com.zupacademy.apass.mercadolivre.model.entity.Produto;
import br.com.zupacademy.apass.mercadolivre.model.entity.ProdutoOpiniao;
import br.com.zupacademy.apass.mercadolivre.model.entity.Usuario;
import br.com.zupacademy.apass.mercadolivre.validation.constraints.ExistsId;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;

public class NovaOpiniaoProdutoRequest {

    @NotNull
    @ExistsId(domainClass = Produto.class, fieldId = "id")
    private Long produtoId;

    @NotBlank
    private String titulo;

    @NotNull
    @Range(min = 1, max = 5)
    private Integer nota;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    public NovaOpiniaoProdutoRequest(@NotNull Long produtoId,
                                     @NotBlank String titulo,
                                     @NotNull @Range(min=1, max=5) Integer nota,
                                     @NotBlank @Size(max=500) String descricao) {
        this.produtoId = produtoId;

        this.titulo = titulo;
        this.nota = nota;
        this.descricao = descricao;
    }

    public ProdutoOpiniao converte(Usuario usuarioLogado, EntityManager entityManager) {
        return new ProdutoOpiniao(this.titulo,
                this.nota,
                this.descricao,
                entityManager.find(Produto.class, this.produtoId),
                usuarioLogado);
    }
}

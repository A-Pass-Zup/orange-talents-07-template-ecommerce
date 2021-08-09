package br.com.zupacademy.apass.mercadolivre.dto.request;

import br.com.zupacademy.apass.mercadolivre.model.Opniao;
import br.com.zupacademy.apass.mercadolivre.model.entity.Produto;
import br.com.zupacademy.apass.mercadolivre.model.entity.Usuario;
import br.com.zupacademy.apass.mercadolivre.validation.constraints.ExistsId;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.*;

public class NovaOpniaoProdutoRequest {

    @NotBlank
    private String titulo;

    @NotNull
    @Range(min = 1, max = 5)
    private Integer nota;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    @NotNull
    @ExistsId(domainClass = Produto.class, fieldId = "id")
    private Long produtoId;

    public NovaOpniaoProdutoRequest(@NotBlank String titulo,
                                    @NotNull @Range(min=1, max=5) Integer nota,
                                    @NotBlank @Size(max=500) String descricao,
                                    @PathVariable @NotNull Long produtoId) {
        this.titulo = titulo;
        this.nota = nota;
        this.descricao = descricao;
        this.produtoId = produtoId;
    }

    public Opniao getProdutoComOpiniao(Usuario usuarioLogado) {
        return new Opniao(this.titulo, this.nota, this.descricao, usuarioLogado);
    }
}

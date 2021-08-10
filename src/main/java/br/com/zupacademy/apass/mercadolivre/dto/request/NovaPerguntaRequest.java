package br.com.zupacademy.apass.mercadolivre.dto.request;


import br.com.zupacademy.apass.mercadolivre.model.entity.Produto;
import br.com.zupacademy.apass.mercadolivre.model.entity.ProdutoPergunta;
import br.com.zupacademy.apass.mercadolivre.model.entity.Usuario;
import br.com.zupacademy.apass.mercadolivre.validation.constraints.ExistsId;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaPerguntaRequest {

    @NotBlank
    private String titulo;

    @NotNull
    @ExistsId(domainClass = Produto.class, fieldId = "id")
    private Long produtoId;

    /**
     * Constutor com os dados obrigatórios.
     * @param titulo
     * @param produtoId
     */
    public NovaPerguntaRequest(@NotBlank String titulo, @NotNull Long produtoId) {
        this.titulo = titulo;
        this.produtoId = produtoId;
    }

    public ProdutoPergunta converte(Usuario usuario, EntityManager entityManager) {
        return new ProdutoPergunta(this.titulo, entityManager.find(Produto.class, this.produtoId), usuario);
    }
}

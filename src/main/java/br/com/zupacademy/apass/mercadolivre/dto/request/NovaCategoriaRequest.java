package br.com.zupacademy.apass.mercadolivre.dto.request;

import br.com.zupacademy.apass.mercadolivre.model.entity.Categoria;
import br.com.zupacademy.apass.mercadolivre.validation.constraints.ExistsId;
import br.com.zupacademy.apass.mercadolivre.validation.constraints.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @ExistsId(domainClass = Categoria.class, fieldId = "id", nullable = true)
    private Long categoriaPaiId;

    /**
     * Construtor para receber os dados.
     *
     * @param nome Obrigatório.
     * @param categoriaPaiId Opcional (pode ser nulo).
     */
    public NovaCategoriaRequest(@NotBlank String nome, Long categoriaPaiId) {
        this.nome = nome;
        this.categoriaPaiId = categoriaPaiId;
    }

    /**
     * Converte para a entidade Categoria.
     *
     * @param entityManager
     * @return
     */
    public Categoria converte(EntityManager entityManager) {

        Categoria categoria = null;
        if(this.categoriaPaiId != null) {
            categoria = entityManager.find(Categoria.class, this.categoriaPaiId);
        }

        return new Categoria(this.nome, categoria);
    }
}

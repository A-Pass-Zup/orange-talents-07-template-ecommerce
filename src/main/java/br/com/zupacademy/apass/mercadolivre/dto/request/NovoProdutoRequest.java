package br.com.zupacademy.apass.mercadolivre.dto.request;

import br.com.zupacademy.apass.mercadolivre.model.entity.Categoria;
import br.com.zupacademy.apass.mercadolivre.model.entity.Produto;
import br.com.zupacademy.apass.mercadolivre.model.entity.Usuario;
import br.com.zupacademy.apass.mercadolivre.validation.constraints.ExistsId;
import org.hibernate.mapping.Collection;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class NovoProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @Size(min = 3)
    private Set<CaracteristicaRequest> caracteristicas;

    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldId = "id")
    private Long categoriaId;

    public NovoProdutoRequest(@NotBlank String nome,
                              @NotNull @Positive BigDecimal valor,
                              @NotNull @PositiveOrZero Integer quantidade,
                              @NotBlank @Size(max = 1000) String descricao,
                              @NotNull @Size(min = 3) Set<CaracteristicaRequest> caracteristicas,
                              @NotNull Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
        this.categoriaId = categoriaId;
    }

    /**
     * Obtém as caracterícias somente como leitura.
     *
     * @return
     */
    public Set<CaracteristicaRequest> getCaracteristicas() {
        return Collections.unmodifiableSet(caracteristicas);
    }

    public Boolean existeCaracteristicaDuplicada() {
        for (var caracteristicaReq : this.caracteristicas) {
            if(this.caracteristicas.stream().anyMatch(c -> c != caracteristicaReq && caracteristicaReq.nomesSaoIguais(c))) {
                return true;
            }
        }
        return  false;
    }

    public Produto converte(Usuario usuario, EntityManager entityManager) {
        return new Produto(
                this.nome,
                this.valor,
                this.quantidade,
                this.descricao,
                entityManager.find(Categoria.class, this.categoriaId),
                this.caracteristicas.stream().map(CaracteristicaRequest::converte).collect(Collectors.toSet()),
                usuario);
    }
}

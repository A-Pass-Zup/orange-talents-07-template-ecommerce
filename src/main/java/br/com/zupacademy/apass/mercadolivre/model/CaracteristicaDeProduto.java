package br.com.zupacademy.apass.mercadolivre.model;

import br.com.zupacademy.apass.mercadolivre.model.entity.ProdutoCaracteristica;
import br.com.zupacademy.apass.mercadolivre.model.entity.Produto;
import io.jsonwebtoken.lang.Assert;

import javax.validation.constraints.NotBlank;
import java.util.Objects;


/**
 * Classe auxiliar para criação do produto.
 */
public class CaracteristicaDeProduto {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    /**
     * Construtor com dados obrigatórios.
     * @param nome
     * @param descricao
     */
    public CaracteristicaDeProduto(@NotBlank String nome, @NotBlank String descricao) {

        Assert.hasText(nome, "A característica (simples) precisa ter um nome!");
        Assert.hasText(descricao, "A característica (simples) precisa ter uma descricao.");

        this.nome = nome;
        this.descricao = descricao;
    }

    /**
     * Obtém o nome da característica.
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém a descrição da característica.
     * @return
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Cria a entidade caracteristica usando um produto inserido.
     * @param produto
     * @return
     */
    public ProdutoCaracteristica converte(Produto produto) {
        return new ProdutoCaracteristica(this.nome, this.descricao, produto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristicaDeProduto that = (CaracteristicaDeProduto) o;
        return Objects.equals(nome, that.nome) && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao);
    }
}

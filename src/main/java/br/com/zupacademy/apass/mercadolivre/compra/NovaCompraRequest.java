package br.com.zupacademy.apass.mercadolivre.compra;

import br.com.zupacademy.apass.mercadolivre.compra.pagamento.GatewayPagamento;
import br.com.zupacademy.apass.mercadolivre.model.entity.Produto;
import br.com.zupacademy.apass.mercadolivre.model.entity.Usuario;
import br.com.zupacademy.apass.mercadolivre.validation.constraints.ExistsId;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {

    @NotNull
    @ExistsId(domainClass = Produto.class, fieldId = "id")
    private Long produtoId;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    private GatewayPagamento gatewayPagamento;

    /**
     * Construtor com os dados obrigattórios.
     *
     * @param produtoId
     * @param quantidade
     * @param gatewayPagamento
     */
    public NovaCompraRequest(@NotNull Long produtoId,
                             @NotNull @Positive Integer quantidade,
                             @NotNull GatewayPagamento gatewayPagamento) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.gatewayPagamento = gatewayPagamento;
    }

    /**
     * Converte em uma compra.
     *
     * @return
     */
    public Compra converte(Usuario usuario, EntityManager entityManager) {
        return new Compra(
                entityManager.find(Produto.class, this.produtoId),
                this.quantidade,
                this.gatewayPagamento,
                usuario
        );
    }
}

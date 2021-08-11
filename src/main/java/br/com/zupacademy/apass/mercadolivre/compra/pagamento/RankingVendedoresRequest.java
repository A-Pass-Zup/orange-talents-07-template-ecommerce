package br.com.zupacademy.apass.mercadolivre.compra.pagamento;

import br.com.zupacademy.apass.mercadolivre.compra.Compra;
import br.com.zupacademy.apass.mercadolivre.model.entity.Usuario;
import br.com.zupacademy.apass.mercadolivre.validation.constraints.ExistsId;

import javax.validation.constraints.NotNull;

public class RankingVendedoresRequest {

    @NotNull
    @ExistsId(domainClass = Compra.class, fieldId = "id")
    private Long idCompra;

    @NotNull
    @ExistsId(domainClass = Usuario.class, fieldId = "id")
    private Long idVendedor;

    /**
     *
     * @param idCompra
     * @param idVendedor
     */
    public RankingVendedoresRequest(@NotNull Long idCompra, @NotNull Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }
}

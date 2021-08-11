package br.com.zupacademy.apass.mercadolivre.compra.pagamento;

import br.com.zupacademy.apass.mercadolivre.compra.Compra;
import br.com.zupacademy.apass.mercadolivre.model.entity.Usuario;
import br.com.zupacademy.apass.mercadolivre.validation.constraints.ExistsId;

import javax.validation.constraints.NotNull;

public class NotaFiscalRequest {

    @NotNull
    @ExistsId(domainClass = Compra.class, fieldId = "id")
    private Long idCompra;

    @NotNull
    @ExistsId(domainClass = Usuario.class, fieldId = "id")
    private Long idComprador;

    /**
     * Construtor com os dados obrigatórios.
     *
     * @param idCompra
     * @param idComprador
     */
    public NotaFiscalRequest(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }
}

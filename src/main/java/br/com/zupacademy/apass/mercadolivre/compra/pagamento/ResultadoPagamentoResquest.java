package br.com.zupacademy.apass.mercadolivre.compra.pagamento;

import br.com.zupacademy.apass.mercadolivre.compra.Compra;
import br.com.zupacademy.apass.mercadolivre.compra.pagamento.Pagamento;

import javax.validation.constraints.NotNull;

public class ResultadoPagamentoResquest {

    @NotNull
    private Object status;

    @NotNull
    private String idPagamento;

    /**
     *
     * @param status
     * @param idPagamento
     */
    public ResultadoPagamentoResquest(Object status, String idPagamento) {
        this.status = status;
        this.idPagamento = idPagamento;
    }

    public Pagamento convert(Compra compra, GatewayPagamento gatewayPagamento) {
        return new Pagamento(compra, this.idPagamento, gatewayPagamento.status(this.status));
    }
}

package br.com.zupacademy.apass.mercadolivre.compra.pagamento;

import br.com.zupacademy.apass.mercadolivre.compra.entity.Compra;
import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

    PAYPAL(new GeradorUrlPayPal()),
    PAGSEGURO(new GeradorUrlPagSeguro());

    private GeradorUrlGatewayPagamento geradorUrlDoGatewayPagamento;

    GatewayPagamento(GeradorUrlGatewayPagamento geradorUrlDoGatewayPagamento) {
        this.geradorUrlDoGatewayPagamento = geradorUrlDoGatewayPagamento;
    }

    public String geraUrlRetorno(Compra compra, String baseUrl) {
        return this.geradorUrlDoGatewayPagamento.gera(compra, baseUrl);
    }
}

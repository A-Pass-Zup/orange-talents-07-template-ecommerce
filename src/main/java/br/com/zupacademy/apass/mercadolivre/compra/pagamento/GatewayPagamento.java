package br.com.zupacademy.apass.mercadolivre.compra.pagamento;

import br.com.zupacademy.apass.mercadolivre.compra.Compra;

public enum GatewayPagamento {

    PAYPAL(new GerenciadorPayPal()),
    PAGSEGURO(new GerenciadorPagSeguro());

    private GerenciadorGatewayPagamento geradorUrlDoGatewayPagamento;

    GatewayPagamento(GerenciadorGatewayPagamento geradorUrlDoGatewayPagamento) {
        this.geradorUrlDoGatewayPagamento = geradorUrlDoGatewayPagamento;
    }

    public String geraUrlRetorno(Compra compra, String baseUrl) {
        return this.geradorUrlDoGatewayPagamento.geraUrl(compra, baseUrl);
    }

    public StatusPagamento status(Object statusGateway) {
        return this.geradorUrlDoGatewayPagamento.status(statusGateway);
    }
}

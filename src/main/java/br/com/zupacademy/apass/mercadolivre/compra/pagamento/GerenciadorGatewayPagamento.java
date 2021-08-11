package br.com.zupacademy.apass.mercadolivre.compra.pagamento;

import br.com.zupacademy.apass.mercadolivre.compra.Compra;

public interface GerenciadorGatewayPagamento {
    /**
     * Gera a URL de acordo com o gateway de pagamento.
     *
     * @param compra
     * @param baseUrl
     * @return
     */
    String geraUrl(Compra compra, String baseUrl);

    /**
     * Pega o status de pagamento recebido pelo gateway e retorna um status de pagamento da aplicação.
     *
     * @param statusDoGeteway
     * @return
     */
    StatusPagamento status(Object statusDoGeteway);
}

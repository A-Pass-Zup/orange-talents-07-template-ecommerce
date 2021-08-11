package br.com.zupacademy.apass.mercadolivre.compra.pagamento;

import br.com.zupacademy.apass.mercadolivre.compra.Compra;

import java.util.Map;

public class GerenciadorPayPal implements GerenciadorGatewayPagamento {

    /**
     * Mapeamento do status de pagamento do gateway para o status de pagamento da aplicação
     */
    final private Map<Integer, StatusPagamento> statusPagamentoGateway = Map.ofEntries(
            Map.entry(0, StatusPagamento.FALHA),
            Map.entry(1, StatusPagamento.SUCESSO));


    @Override
    public String geraUrl(Compra compra, String baseUrl) {
        return "paypal.com?buyerId=" + compra.getIdentificador() +
               "&redirectUrl=" + baseUrl + "/paypal/" + compra.getIdentificador();
    }

    @Override
    public StatusPagamento status(Object statusDoGeteway) {
        return this.statusPagamentoGateway.get((Integer) statusDoGeteway);
    }
}

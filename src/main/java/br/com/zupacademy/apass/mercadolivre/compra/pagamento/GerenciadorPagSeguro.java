package br.com.zupacademy.apass.mercadolivre.compra.pagamento;

import br.com.zupacademy.apass.mercadolivre.compra.Compra;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class GerenciadorPagSeguro implements GerenciadorGatewayPagamento {

    /**
     * Mapeamento do status de pagamento do gateway para o status de pagamento da aplicação
     */
    final private Map<String, StatusPagamento> statusPagamentoGateway = Map.ofEntries(
            Map.entry("SUCESSO", StatusPagamento.SUCESSO),
            Map.entry("ERRO", StatusPagamento.FALHA));


    @Override
    public String geraUrl(Compra compra, String baseUrl) {
        return "pagseguro.com?returnId=" + compra.getIdentificador() +
               "&redirectUrl=" + baseUrl + "/pagseguro/" + compra.getIdentificador();
    }

    @Override
    public StatusPagamento status(@NotNull Object statusPagamentoGeteway) {
        return this.statusPagamentoGateway.get((String) statusPagamentoGeteway);
    }

}

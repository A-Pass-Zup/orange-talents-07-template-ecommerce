package br.com.zupacademy.apass.mercadolivre.compra.pagamento;

import br.com.zupacademy.apass.mercadolivre.compra.entity.Compra;

public class GeradorUrlPagSeguro implements GeradorUrlGatewayPagamento {
    @Override
    public String gera(Compra compra, String baseUrl) {
        return "pagseguro.com?returnId=" + compra.getIdentificador() +
               "&redirectUrl=" + baseUrl + "/retorno-pagseguro/" + compra.getIdentificador();
    }
}

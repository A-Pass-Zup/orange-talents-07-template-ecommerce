package br.com.zupacademy.apass.mercadolivre.compra.pagamento;

import br.com.zupacademy.apass.mercadolivre.compra.entity.Compra;
import org.springframework.web.util.UriComponentsBuilder;

public class GeradorUrlPayPal implements GeradorUrlGatewayPagamento {
    @Override
    public String gera(Compra compra, String baseUrl) {
        return "paypal.com?buyerId=" + compra.getIdentificador() +
               "&redirectUrl=" + baseUrl + "/retorno-paypal/" + compra.getIdentificador();
    }
}

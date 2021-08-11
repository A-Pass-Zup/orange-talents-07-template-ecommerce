package br.com.zupacademy.apass.mercadolivre.compra.pagamento;

import br.com.zupacademy.apass.mercadolivre.compra.entity.Compra;
import org.springframework.web.util.UriComponentsBuilder;

public interface GeradorUrlGatewayPagamento {
    String gera(Compra compra, String baseUrl);
}

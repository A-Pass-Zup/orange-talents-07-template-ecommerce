package br.com.zupacademy.apass.mercadolivre.compra.pagamento;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RankingVendedoresService {

    public void proccessa(Pagamento pagamento) {

        // Essa código foi feito com base no material de suporte

        RestTemplate restTemplate = new RestTemplate();

        final var request = new RankingVendedoresRequest(pagamento.getIdCompra(), pagamento.getIdVendedor());

        restTemplate.postForEntity("http://localhost:8080/api/v1/outros-sistemas/ranking-vendedores", request, String.class);

    }
}

package br.com.zupacademy.apass.mercadolivre.compra.pagamento;

import br.com.zupacademy.apass.mercadolivre.compra.pagamento.Pagamento;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotaFiscalService {

    public void processa(Pagamento pagamento) {

        // Essa código foi feito com base no material de suporte

        RestTemplate restTemplate = new RestTemplate();

        final var notaFiscalRequest = new NotaFiscalRequest(pagamento.getIdCompra(), pagamento.getIdComprador());

        restTemplate.postForEntity("http://localhost:8080/api/v1/outros-sistemas/nota-fiscal", notaFiscalRequest, String.class);

    }
}

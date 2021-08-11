package br.com.zupacademy.apass.mercadolivre.compra.pagamento;

import br.com.zupacademy.apass.mercadolivre.compra.pagamento.Pagamento;
import br.com.zupacademy.apass.mercadolivre.email.EscritorDeEmail;

public class EscritorEmailCompraSucesso implements EscritorDeEmail {

    private final Pagamento pagamento;

    public EscritorEmailCompraSucesso(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    @Override
    public String escreve() {
        return "Para: " + pagamento.getLoginComprador() + '\n' +
                "Mensagem: O pagamento da compra '" + pagamento.getIdentificadorCompra() + "', do produto '" + pagamento.getNomeProduto() + " foi efetuado com sucesso!" +
                "Código do pagamento: " + pagamento.getIdentificador();

    }
}

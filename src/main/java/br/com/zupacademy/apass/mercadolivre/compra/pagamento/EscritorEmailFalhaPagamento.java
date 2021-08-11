package br.com.zupacademy.apass.mercadolivre.compra.pagamento;

import br.com.zupacademy.apass.mercadolivre.email.EscritorDeEmail;

public class EscritorEmailFalhaPagamento implements EscritorDeEmail {

    private Pagamento pagamento;
    private String baseUrl;

    public EscritorEmailFalhaPagamento(Pagamento pagamento, String baseUrl) {
        this.pagamento = pagamento;
        this.baseUrl = baseUrl;
    }

    @Override
    public String escreve() {
        return "Para: " + this.pagamento.getLoginComprador() +
                "Mensagem: A tentativa de pagamento (" + pagamento.getIdentificador() + ") para a compra '" + pagamento.getIdentificador() + "' do produto '" + pagamento.getNomeProduto() + "' falhou!\n" +
                "Clinque <a href=\"" + pagamento.getLinkPagamento(baseUrl) + "\"> aqui </a> para tentar realizar o pagamento novamente. " +
                "Link :" + pagamento.getLinkPagamento(baseUrl);
    }
}

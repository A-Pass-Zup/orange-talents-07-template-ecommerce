package br.com.zupacademy.apass.mercadolivre.email;

import br.com.zupacademy.apass.mercadolivre.compra.Compra;

public class EscritorEmailNovaCompra implements EscritorDeEmail {

    private Compra compra;

    public EscritorEmailNovaCompra(Compra compra) {
        this.compra = compra;
    }

    @Override
    public String escreve() {
        return new StringBuilder()
                .append("Para: ").append(this.compra.getLoginVendedor() + '\n')
                .append("Mensagem: ").append("O usuário " + this.compra.getLoginComprador() + " deseja comprar " + this.compra.getQuantidade() + " unidade(s) do produto '" + this.compra.getNomeProduto() + "'")
                .toString();
    }
}

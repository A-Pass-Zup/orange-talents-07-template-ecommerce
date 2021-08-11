package br.com.zupacademy.apass.mercadolivre.compra.pagamento;

public class StatusPagamentoResponse {

    private String pagamento;

    private String compra;

    private String status;

    public StatusPagamentoResponse(Pagamento pagamento) {
        this.pagamento = pagamento.getIdentificador();
        this.compra = pagamento.getIdentificadorCompra();
        this.status = pagamento.getStatus().toString();
    }

    public String getPagamento() {
        return pagamento;
    }

    public String getCompra() {
        return compra;
    }

    public String getStatus() {
        return status;
    }
}

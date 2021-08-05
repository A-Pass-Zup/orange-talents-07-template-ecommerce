package br.com.zupacademy.apass.mercadolivre.dto.response;

public class ErroValidacaoResponse {
    private String atributo;
    private String mensagem;
    private String valor;

    public ErroValidacaoResponse(String atributo, String valor, String mensagem) {
        this.atributo = atributo;
        this.valor = valor;
        this.mensagem = mensagem;
    }

    public String getAtributo() {
        return atributo;
    }

    public String getValor() {
        return valor;
    }

    public String getMensagem() {
        return mensagem;
    }
}

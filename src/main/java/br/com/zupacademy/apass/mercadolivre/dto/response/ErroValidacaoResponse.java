package br.com.zupacademy.apass.mercadolivre.dto.response;

public class ErroValidacaoResponse {
    private String atributo;
    private String mensagem;

    public ErroValidacaoResponse(String atributo, String mensagem) {
        this.atributo = atributo;
        this.mensagem = mensagem;
    }

    public String getAtributo() {
        return atributo;
    }

    public String getMensagem() {
        return mensagem;
    }
}

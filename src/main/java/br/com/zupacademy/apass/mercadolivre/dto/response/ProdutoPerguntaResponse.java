package br.com.zupacademy.apass.mercadolivre.dto.response;

import br.com.zupacademy.apass.mercadolivre.model.entity.ProdutoPergunta;

import java.time.format.DateTimeFormatter;

public class ProdutoPerguntaResponse {

    private String titulo;

    private String interessado;

    private String DataHoraCadastro;

    public ProdutoPerguntaResponse(ProdutoPergunta pergunta) {
        this.titulo = pergunta.getTitulo();
        this.interessado = pergunta.getUsuario().getLogin();
        DataHoraCadastro = pergunta.getDataHoraCadastro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public String getTitulo() {
        return titulo;
    }

    public String getInteressado() {
        return interessado;
    }

    public String getDataHoraCadastro() {
        return DataHoraCadastro;
    }
}

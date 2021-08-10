package br.com.zupacademy.apass.mercadolivre.dto.response;

import br.com.zupacademy.apass.mercadolivre.model.entity.ProdutoOpiniao;

public class ProdutoOpiniaoResponse {

    private final String titulo;
    private final Integer nota;
    private final String descricao;

    public ProdutoOpiniaoResponse(ProdutoOpiniao opiniao) {
        this.titulo = opiniao.getTitulo();
        this.nota = opiniao.getNota();
        this.descricao = opiniao.getDescricao();
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getNota() {
        return nota;
    }

    public String getDescricao() {
        return descricao;
    }
}

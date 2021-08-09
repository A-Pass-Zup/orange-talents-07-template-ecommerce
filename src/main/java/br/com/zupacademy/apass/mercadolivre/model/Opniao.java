package br.com.zupacademy.apass.mercadolivre.model;

import br.com.zupacademy.apass.mercadolivre.model.entity.Usuario;

public class Opniao {
    private final String titulo;
    private final Integer nota;
    private final String descricao;
    private final Usuario usuarioLogado;

    public Opniao(String titulo, Integer nota, String descricao, Usuario usuarioLogado) {
        this.titulo = titulo;
        this.nota = nota;
        this.descricao = descricao;
        this.usuarioLogado = usuarioLogado;
    }
}

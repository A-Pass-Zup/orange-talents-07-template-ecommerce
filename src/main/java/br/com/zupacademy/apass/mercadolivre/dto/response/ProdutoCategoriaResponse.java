package br.com.zupacademy.apass.mercadolivre.dto.response;

import br.com.zupacademy.apass.mercadolivre.model.entity.Categoria;

public class ProdutoCategoriaResponse {

    private String nome;

    private ProdutoCategoriaResponse categoriaPai;

    public ProdutoCategoriaResponse(Categoria categoria) {
        this.nome = categoria.getNome();

        if(categoria.getCategoriaPai() != null)
            this.categoriaPai = new ProdutoCategoriaResponse(categoria.getCategoriaPai());
    }

    public String getNome() {
        return nome;
    }

    public ProdutoCategoriaResponse getCategoriaPai() {
        return categoriaPai;
    }
}

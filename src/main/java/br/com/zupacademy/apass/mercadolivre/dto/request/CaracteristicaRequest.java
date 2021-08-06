package br.com.zupacademy.apass.mercadolivre.dto.request;

import br.com.zupacademy.apass.mercadolivre.model.CaracteristicaSimples;
import br.com.zupacademy.apass.mercadolivre.model.entity.Produto;

import javax.validation.constraints.NotBlank;

public class CaracteristicaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public CaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public CaracteristicaSimples converte() {
        return new CaracteristicaSimples(this.nome, this.descricao);
    }
}

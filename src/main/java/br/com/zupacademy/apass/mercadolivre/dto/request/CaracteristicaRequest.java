package br.com.zupacademy.apass.mercadolivre.dto.request;

import br.com.zupacademy.apass.mercadolivre.model.CaracteristicaDeProduto;
import br.com.zupacademy.apass.mercadolivre.util.DiacriticoUtil;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CaracteristicaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public CaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public CaracteristicaDeProduto converte() {
        return new CaracteristicaDeProduto(this.nome, this.descricao);
    }

    /**
     * Verifica se os nomes das características são iguais ignorando maiúculas, minúsculas e acentos.
     *
     * @param caracteristicaRequest
     * @return
     */
    public Boolean nomesSaoIguais(CaracteristicaRequest caracteristicaRequest) {
        return DiacriticoUtil.removeDiacriticos(this.nome).equalsIgnoreCase(DiacriticoUtil.removeDiacriticos(caracteristicaRequest.nome));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristicaRequest that = (CaracteristicaRequest) o;
        return Objects.equals(nome, that.nome) && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao);
    }

    @Override
    public String toString() {
        return '{' +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}

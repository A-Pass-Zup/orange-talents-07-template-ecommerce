package br.com.zupacademy.apass.mercadolivre.model.entity;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoriaPai;


    @Deprecated
    protected Categoria() {
    }

    /**
     * Construtor para criar uma categoria.
     * @param nome Obrigatório.
     * @param categoriaPai Opcional (pode ser nulo).
     */
    public Categoria(@NotBlank String nome, Categoria categoriaPai) {
        Assert.hasText(nome, "Não pode criar categoria sem nome!");

        this.nome = nome;
        this.categoriaPai = categoriaPai;
    }
}

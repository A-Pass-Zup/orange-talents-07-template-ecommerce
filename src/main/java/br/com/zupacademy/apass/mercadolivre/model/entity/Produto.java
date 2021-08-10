package br.com.zupacademy.apass.mercadolivre.model.entity;

import br.com.zupacademy.apass.mercadolivre.model.CaracteristicaDeProduto;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal valor;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Integer quantidade;

    @NotBlank
    @Column(nullable = false, length = 1000)
    private String descricao;

    @NotNull
    @ManyToOne(optional = false)
    private Categoria categoria;

    @Size(min=3)
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private final Set<ProdutoCaracteristica> produtoCaracteristicas = new HashSet<>();

    @NotNull
    @ManyToOne(optional = false)
    private Usuario usuarioCadastro;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ProdutoImagem> imagens;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<ProdutoOpiniao> opinioes;

    @Deprecated
    protected Produto() {
    }

    public Produto(@NotBlank String nome,
                   @NotNull @Positive BigDecimal valor,
                   @NotNull @PositiveOrZero Integer quantidade,
                   @NotBlank @Size(max=1000) String descricao,
                   @NotNull Categoria categoria,
                   @NotNull @Size(min=3) Set<CaracteristicaDeProduto> caracteristicas,
                   @NotNull Usuario usuarioCadastro) {

        Assert.hasText(nome, "Não pode criar produto sem nome!");
        Assert.isTrue(valor.compareTo(BigDecimal.ZERO) > 0, "O valor do produto precisa ser > 0.0!");
        Assert.isTrue(quantidade >= 0, "A quantidade do produto precisa ser >= 0!");

        Assert.hasText(descricao, "Não pode criar produto sem descrição!");
        Assert.isTrue(descricao.length() <= 1000, "A descrição do produto precisa ter no máximo 1000 caracteres!");

        Assert.notNull(categoria, "Não pode crirar produto sem categoria!");

        Assert.notNull(caracteristicas, "Não pode criar produto sem caracteríticas!");
        Assert.isTrue(caracteristicas.size() >= 3, "Produto precisa ter pelo menos 3 características!");

        Assert.notNull(usuarioCadastro, "Não pode criar produto sem o usuário!");

        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;

        this.produtoCaracteristicas.addAll(caracteristicas.stream()
                .map(cs->cs.converte(this))
                .collect(Collectors.toSet()));

        this.usuarioCadastro = usuarioCadastro;
    }

    /**
     * Adicina imagens ao produto.
     *
     * @param imagem
     */
    public void addImagem(String imagem) {
        this.imagens.add(new ProdutoImagem(this, imagem));
    }


    public void addOpniao(ProdutoOpiniao produtoOpiniao) {
        Assert.state(produtoOpiniao.pertenceAoProduto(this), "A opnião passada não pertence ao produto!");

        this.opinioes.add(produtoOpiniao);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", caracteristicas=" + produtoCaracteristicas +
                '}';
    }

}

package br.com.zupacademy.apass.mercadolivre.model.entity;

import br.com.zupacademy.apass.mercadolivre.model.CaracteristicaDeProduto;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.*;
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
    private Set<ProdutoImagem> imagens = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<ProdutoOpiniao> opinioes = new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<ProdutoPergunta> perguntas = new ArrayList<>();

    /**
     * Construtor padrão para a JPA. Não use.
     */
    @Deprecated
    protected Produto() {
    }

    /**
     * Construtor com os dados obrigatórios.
     *
     * @param nome
     * @param valor
     * @param quantidade
     * @param descricao
     * @param categoria
     * @param caracteristicas
     * @param usuarioCadastro
     */
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
     * Adicina um imagem ao produto.
     *
     * @param imagem
     */
    public void addImagem(String imagem) {
        this.imagens.add(new ProdutoImagem(this, imagem));
    }

    /**
     * Adiciona uma opnião ao produto.
     *
     * @param produtoOpiniao
     */
    public void addOpniao(ProdutoOpiniao produtoOpiniao) {
        Assert.state(produtoOpiniao.pertenceAoProduto(this), "A opnião passada não pertence ao produto!");
        Assert.state(!this.jaExiste(produtoOpiniao), "Não pode adicionar ao produto, uma opnião que já existe!");

        this.opinioes.add(produtoOpiniao);
    }

    /**
     * Adiciona uma pergunta ao produto.
     *
     * @param produtoPergunta
     */
    public void addPergunta(ProdutoPergunta produtoPergunta) {
        Assert.state(produtoPergunta.sobreOProduto(this), "O produto da pergunta deve ser igual ao produto que deseja inserir a pergunta!");
        Assert.state(!this.jaExiste(produtoPergunta), "Não pode adicionar ao produto, uma pergunta que já existe!");

        this.perguntas.add(produtoPergunta);
    }

    /**
     * Verifica se uma instância já foi adicionada ao produto.
     *
     * @param pergunta
     * @return
     */
    public boolean jaExiste(ProdutoPergunta pergunta) {
        return this.perguntas.stream().anyMatch(p -> p == pergunta);
    }

    public boolean jaExiste(ProdutoOpiniao opiniao) {
        return this.opinioes.stream().anyMatch(o -> o == opiniao);
    }

    /**
     * Obtém o nome do produto.
     *
     * @return
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Obtém o usuário dono do produto.
     *
     * @return
     */
    public Usuario getUsuarioCadastro() {
        return usuarioCadastro;
    }

    /**
     * Obtém o valor do produto.
     *
     * @return
     */
    public BigDecimal getValor() {
        return this.valor;
    }

    /**
     * Obtém a quantidade dísponível do produto.
     *
     * @return
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * Obtém a descrição do produto.
     *
     * @return
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Obtém a categoria do produto.
     *
     * @return
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Obtém as caracteríticas do produto.
     *
     * @return
     */
    public Set<ProdutoCaracteristica> getProdutoCaracteristicas() {
        return Collections.unmodifiableSet(produtoCaracteristicas);
    }

    /**
     * Obtém as imagens do produto.
     *
     * @return
     */
    public Set<ProdutoImagem> getImagens() {
        return Collections.unmodifiableSet(imagens);
    }

    /**
     * Diminui a quantidade do produto.
     *
     * @param quantidade Precisa ser positivo (> 0) não nulo.
     */
    public void diminuiEstoque(@NotNull @Positive Integer quantidade) {

        Assert.isTrue(quantidade > 0, "A quantidade para diminuir do estoque precisa ser positiva > 0!");
        Assert.isTrue(quantidade <= this.quantidade, "Não pode diminuir a quantidade do produto mais do que está presente no estoque!");

       this.quantidade -= quantidade;
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

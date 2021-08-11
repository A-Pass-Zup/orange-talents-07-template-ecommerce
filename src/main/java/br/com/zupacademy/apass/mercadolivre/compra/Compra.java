package br.com.zupacademy.apass.mercadolivre.compra;

import br.com.zupacademy.apass.mercadolivre.compra.pagamento.GatewayPagamento;
import br.com.zupacademy.apass.mercadolivre.compra.pagamento.Pagamento;
import br.com.zupacademy.apass.mercadolivre.model.entity.Produto;
import br.com.zupacademy.apass.mercadolivre.model.entity.Usuario;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;
import java.util.UUID;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    private Produto produto;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GatewayPagamento gatewayPagamento;

    @NotNull
    @Column(unique = true, nullable = false)
    private String identificador;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusCompra status;

    @ManyToOne(optional = false)
    private Usuario comprador;

    @OneToMany(mappedBy = "compra", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Pagamento> pagamentos;

    /**
     * Construtor usado pela JPA. Não utilize.
     */
    @Deprecated
    protected Compra() {
        this.finalizaSeExistirPagamentoComSucesso();
    }

    /**
     * Construtor com os dados obrigatórios.
     *
     * @param produto
     * @param quantidade
     * @param gatewayPagamento
     * @param comprador
     */
    public Compra(@NotNull Produto produto,
                  @NotNull @Positive Integer quantidade,
                  @NotNull GatewayPagamento gatewayPagamento,
                  @NotNull Usuario comprador) {

        Assert.notNull(produto, "Não pode criar uma compra sem o produto!");

        Assert.isTrue(quantidade > 0, "A quantidade de compra do produto precisa ser > 0!");

        Assert.notNull(gatewayPagamento, "Não pode criar uma comprar sem o gateway de pagamento!");

        Assert.notNull(comprador, "Não pode criar uma compra sem ter um comprador!");

        this.produto = produto;
        this.quantidade = quantidade;
        this.gatewayPagamento = gatewayPagamento;
        this.comprador = comprador;
        this.identificador = UUID.randomUUID().toString();
        this.status = StatusCompra.INICIALIZADA;

        this.finalizaSeExistirPagamentoComSucesso();
    }

    /**
     * Obtém o id da compra.
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getIdentificador() {
        return this.identificador;
    }


    /**
     * Retornar o login do usuário que fez a compra.
     *
     * @return
     */
    public String getLoginComprador() {
        return this.comprador.getLogin();
    }

    /**
     * Obtém o id do usuário que fez a compra.
     *
     * @return
     */
    public Long getIdComprador() {
        return this.comprador.getId();
    }

    /**
     * Obtém o login do dono do produto que está sendo comprado.
     *
     * @return
     */
    public String getLoginVendedor() {
        return this.produto.getUsuarioCadastro().getLogin();
    }

    /**
     * Obtém o id do venderdor (dono) do produto.
     *
     * @return
     */
    public Long getIdVendedor() {
        return this.produto.getUsuarioCadastro().getId();
    }

    /**
     * Obtém o nome do produto.
     *
     * @return
     */
    public String getNomeProduto() {
        return this.produto.getNome();
    }

    /**
     * Obtém a quantidade de compra do produto.
     *
     * @return
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * Processa a compra, abatendo a quantidade do estoque.
     *
     * @return
     */
    public void processa() {
        this.naoPermiteEfetuarOperacaoComStatusFinalizada("Não pode processar a compra com status paga/finaliada!");

        this.produto.diminuiEstoque(this.quantidade);
    }

    /**
     * Obtém o link para pagamento da compra.
     *
     * @param baseUrl
     */
    public String geraLinkParaPagamento(String baseUrl) {
        return this.gatewayPagamento.geraUrlRetorno(this, baseUrl);
    }

    /**
     * Tenta efetuar o pagamento da compra e altera seus status para finalizada/paga.
     *
     * @param pagamento
     * @return
     * @throws IllegalCallerException Lança essa exceção se a compra já tiver sido finalizada!
     */
    public void addPagamento(Pagamento pagamento) throws IllegalCallerException {
        this.naoPermiteEfetuarOperacaoComStatusFinalizada("Não pode efetuar o pagamente de uma compra com status paga/finalizada!");

        Assert.isTrue(this.pagamentos.stream().anyMatch(pagamento::equals), "Não pode adicionar uma pagamento que já existe!");

        this.pagamentos.add(pagamento);
    }

    /**
     * Finaliza a compra se existir alguma pagamento efetuado com sucesso.
     */
    private void finalizaSeExistirPagamentoComSucesso() {
        if (this.pagamentos.stream().anyMatch(Pagamento::sucesso)) {
            this.status = StatusCompra.FINALIZADA;
        }
    }

    /**
     * Não permite efetuar a operação quando a compra está paga/finalizada.
     *
     * @param mensagem
     * @throws IllegalCallerException
     */
    private void naoPermiteEfetuarOperacaoComStatusFinalizada(String mensagem) throws IllegalCallerException {
        this.finalizaSeExistirPagamentoComSucesso();

        if (this.status.equals(StatusCompra.FINALIZADA)) {
            throw new IllegalCallerException(mensagem);
        }
    }
}

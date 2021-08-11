package br.com.zupacademy.apass.mercadolivre.compra.entity;

import br.com.zupacademy.apass.mercadolivre.compra.CompraStatus;
import br.com.zupacademy.apass.mercadolivre.compra.pagamento.GatewayPagamento;
import br.com.zupacademy.apass.mercadolivre.model.entity.Produto;
import br.com.zupacademy.apass.mercadolivre.model.entity.Usuario;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
    private CompraStatus status;

    @ManyToOne(optional = false)
    private Usuario comprador;

    /**
     * Construtor usado pela JPA. Não utilize.
     */
    @Deprecated
    protected Compra(){
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
        this.status = CompraStatus.INICIALIZADA;
    }

    public String getIdentificador() {
        return this.identificador;
    }


    /**
     * Retornar o login do comprador.
     *
     * @return
     */
    public String getLoginComprador() {
        return this.comprador.getLogin();
    }

    /**
     * Obtém o login do dono do produto que está sendo comprado.
     *
     * @return
     */
    public String getLoginDonoProduto() {
        return this.produto.getUsuarioCadastro().getLogin();
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
     *
     * @param baseUrl
     * @return
     */
    public String processa(String baseUrl) {
        this.produto.diminuiEstoque(this.quantidade);
        return this.gatewayPagamento.geraUrlRetorno(this, baseUrl);
    }
}

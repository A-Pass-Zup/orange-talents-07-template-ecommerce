package br.com.zupacademy.apass.mercadolivre.compra.pagamento;

import br.com.zupacademy.apass.mercadolivre.compra.Compra;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    private Compra compra;

    @NotBlank
    @NotNull
    @Column(unique = true, nullable = false)
    private String identificador;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @NotNull
    private LocalDateTime dataHoraRegistro = LocalDateTime.now();

    @Deprecated
    protected Pagamento() {
    }

    /**
     *
     * @param compra
     * @param identificador
     * @param status
     */
    public Pagamento(@NotNull Compra compra,
                     @NotBlank String identificador,
                     @NotNull StatusPagamento status) {
        this.compra = compra;
        this.identificador = identificador;
        this.status = status;
    }

    /**
     * Obtém o login do comprador/pagador da compra.
     *
     * @return
     */
    public String getLoginComprador() {
        return this.compra.getLoginComprador();
    }

    /**
     * Obtém o identificador da compra para pagamento.
     *
     * @return
     */
    public String getIdentificadorCompra() {
        return this.compra.getIdentificador();
    }

    /**
     * Obté o nome do produto da compra para pagamento.
     *
     * @return
     */
    public String getNomeProduto() {
       return this.compra.getNomeProduto();
    }

    /**
     * Obtém o identificador do pagamento.
     *
     * @return
     */
    public String getIdentificador() {
        return this.identificador;
    }

    /**
     * Obtém o link para pagamento da compra.
     *
     * @param baseUrl
     * @return
     */
    public String getLinkPagamento(String baseUrl) {
        return this.compra.geraLinkParaPagamento(baseUrl);
    }

    /**
     * Obtém o id da compra para pagamento.
     *
     * @return
     */
    public Long getIdCompra() {
        return this.compra.getId();
    }

    /**
     * Obtém o id do usuário que fez a compra do produto.
     *
     * @return
     */
    public Long getIdComprador() {
        return this.compra.getIdComprador();
    }

    /**
     * Obtém o id do vendedor do produto da compra.
     *
     * @return
     */
    public Long getIdVendedor() {
        return this.compra.getIdVendedor();
    }

    /**
     * Obtém o status de pagamento.
     *
     * @return
     */
    public StatusPagamento getStatus() {
        return status;
    }

    /**
     * Processa o pagamento
     */
    public void processa() {
        this.compra.addPagamento(this);
    }

    /**
     * Retorna se o pagamento foi efetuado com sucesso ou não.
     *
     * @return
     */
    public Boolean sucesso() {
        return this.status.equals(StatusPagamento.SUCESSO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(identificador, pagamento.identificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }
}

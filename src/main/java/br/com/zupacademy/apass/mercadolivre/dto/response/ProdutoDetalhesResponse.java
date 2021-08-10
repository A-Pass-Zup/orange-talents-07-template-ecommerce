package br.com.zupacademy.apass.mercadolivre.dto.response;

import br.com.zupacademy.apass.mercadolivre.model.entity.Produto;
import br.com.zupacademy.apass.mercadolivre.model.entity.ProdutoImagem;
import br.com.zupacademy.apass.mercadolivre.model.entity.ProdutoOpiniao;
import br.com.zupacademy.apass.mercadolivre.model.entity.ProdutoPergunta;
import br.com.zupacademy.apass.mercadolivre.repository.ProdutoOpiniaoRespository;
import br.com.zupacademy.apass.mercadolivre.repository.ProdutoPerguntaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoDetalhesResponse {

    private String nome;

    private BigDecimal valor;

    private Integer quantidade;

    private String descricao;

    private ProdutoCategoriaResponse categoria;

    private String dono;

    private Set<String> imagens;

    private Page<ProdutoOpiniaoResponse> opnioes;

    private double mediaDeNotas;

    private Page<ProdutoPerguntaResponse> perguntas;

    public ProdutoDetalhesResponse(Produto produto, ProdutoOpiniaoRespository opiniaoRespository, ProdutoPerguntaRepository perguntaRepository) {

        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidade = produto.getQuantidade();
        this.descricao = produto.getDescricao();
        this.categoria = new ProdutoCategoriaResponse(produto.getCategoria());
        this.dono = produto.getUsuarioCadastro().getLogin();
        this.imagens = produto.getImagens().stream().map(ProdutoImagem::getImagem).collect(Collectors.toSet());

        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));

        this.opnioes = opiniaoRespository.getByProduto(produto, pageable).map(ProdutoOpiniaoResponse::new);
        this.mediaDeNotas = opiniaoRespository.mediaDeNotasDoProduto(produto);

        this.perguntas = perguntaRepository.getByProduto(produto, pageable).map(ProdutoPerguntaResponse::new);
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public ProdutoCategoriaResponse getCategoria() {
        return categoria;
    }

    public String getDono() {
        return dono;
    }

    public Set<String> getImagens() {
        return imagens;
    }

    public Page<ProdutoOpiniaoResponse> getOpnioes() {
        return opnioes;
    }

    public double getMediaDeNotas() {
        return mediaDeNotas;
    }

    public Page<ProdutoPerguntaResponse> getPerguntas() {
        return perguntas;
    }
}

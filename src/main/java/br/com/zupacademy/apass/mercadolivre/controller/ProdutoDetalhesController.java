package br.com.zupacademy.apass.mercadolivre.controller;

import br.com.zupacademy.apass.mercadolivre.dto.response.ProdutoDetalhesResponse;
import br.com.zupacademy.apass.mercadolivre.dto.response.ProdutoOpiniaoResponse;
import br.com.zupacademy.apass.mercadolivre.dto.response.ProdutoPerguntaResponse;
import br.com.zupacademy.apass.mercadolivre.model.entity.Produto;
import br.com.zupacademy.apass.mercadolivre.model.entity.ProdutoOpiniao;
import br.com.zupacademy.apass.mercadolivre.model.entity.ProdutoPergunta;
import br.com.zupacademy.apass.mercadolivre.repository.ProdutoOpiniaoRespository;
import br.com.zupacademy.apass.mercadolivre.repository.ProdutoPerguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoDetalhesController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProdutoOpiniaoRespository opiniaoRespository;

    @Autowired
    private ProdutoPerguntaRepository produtoPerguntaRepository;

    @GetMapping("{produtoId}/detalhes")
    public ProdutoDetalhesResponse detalhaProduto(@PathVariable Long produtoId) {
        return new ProdutoDetalhesResponse(
                this.verificaProduto(produtoId),
                this.opiniaoRespository,
                this.produtoPerguntaRepository);

    }

    @GetMapping("{produtoId}/perguntas")
    public Page<ProdutoOpiniaoResponse> listaOpiniao(@PathVariable Long produtoId, @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return this.opiniaoRespository.
                getByProduto(
                        this.verificaProduto(produtoId), pageable)
                .map(ProdutoOpiniaoResponse::new);
    }

    @GetMapping("{produtoId}/opinioes")
    public Page<ProdutoPerguntaResponse> listaPerguntas(@PathVariable Long produtoId, @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return this.produtoPerguntaRepository.
                getByProduto(
                        this.verificaProduto(produtoId), pageable)
                .map(ProdutoPerguntaResponse::new);
    }

    /**
     * Verifica se o produto existe e o retorna, se não, lança um not found.
     *
     * @param produtoId
     * @return
     */
    private Produto verificaProduto(Long produtoId) {
        return Optional.of(this.entityManager.find(Produto.class, produtoId))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nâo foi encontrado o produto com código " + produtoId));
    }
}

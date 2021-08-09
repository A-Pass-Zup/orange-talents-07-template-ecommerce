package br.com.zupacademy.apass.mercadolivre.controller;

import br.com.zupacademy.apass.mercadolivre.dto.request.NovasImagensProdutoRequest;
import br.com.zupacademy.apass.mercadolivre.model.entity.Produto;
import br.com.zupacademy.apass.mercadolivre.service.SalvaProdutoImagensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/produto/imagens")
public class ProdutoImagensController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SalvaProdutoImagensService salvaProdutoImagensService;

    @PostMapping
    @Transactional
    public void cadastro(@Valid NovasImagensProdutoRequest novasImagensProdutoRequest) {

        Produto produto = novasImagensProdutoRequest.getProduto(this.entityManager);

        var imagens  = this.salvaProdutoImagensService.
                salva(novasImagensProdutoRequest.getImagens());

        imagens.stream().forEach(produto::addImagem);

        this.entityManager.merge(produto);
    }
}

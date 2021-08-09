package br.com.zupacademy.apass.mercadolivre.controller;

import br.com.zupacademy.apass.mercadolivre.dto.request.NovasImagensProdutoRequest;
import br.com.zupacademy.apass.mercadolivre.model.entity.Produto;
import br.com.zupacademy.apass.mercadolivre.service.SalvaProdutoImagensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/produto/imagens")
public class ProdutoImagensController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SalvaProdutoImagensService salvaProdutoImagensService;

    @PostMapping
    @Transactional
    public void cadastro(NovasImagensProdutoRequest novasImagensProdutoRequest) {

        Produto produto = novasImagensProdutoRequest.converteParaProduto(this.entityManager);

        var imagens  = this.salvaProdutoImagensService.
                salva(novasImagensProdutoRequest.getImagens());

        imagens.stream().forEach(produto::addImagem);

        this.entityManager.merge(produto);
    }
}

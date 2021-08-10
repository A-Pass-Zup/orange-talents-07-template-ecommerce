package br.com.zupacademy.apass.mercadolivre.controller;

import br.com.zupacademy.apass.mercadolivre.dto.request.NovaOpiniaoProdutoRequest;
import br.com.zupacademy.apass.mercadolivre.security.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoOpiniaoController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping(value = "/opniao")
    @Transactional
    public void cadastra(@RequestBody @Valid NovaOpiniaoProdutoRequest novaOpniaoProdutoRequest, @AuthenticationPrincipal User usuarioLogado) {

        this.entityManager.merge(
                novaOpniaoProdutoRequest.converteParaProdutoComOpiniao(
                        usuarioLogado.getUsuario(), this.entityManager));
    }
}

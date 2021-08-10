package br.com.zupacademy.apass.mercadolivre.controller;

import br.com.zupacademy.apass.mercadolivre.dto.request.NovaPerguntaRequest;
import br.com.zupacademy.apass.mercadolivre.security.User;
import br.com.zupacademy.apass.mercadolivre.service.MensageiroEmailService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/produto/pergunta")
public class ProdutoPerguntaController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MensageiroEmailService mensageiroEmailService;

    @PostMapping
    @Transactional
    public void cadastra(@RequestBody @Valid NovaPerguntaRequest novaPerguntaRequest, @AuthenticationPrincipal User usuarioLogado) {

        var pergunta = novaPerguntaRequest.converte(usuarioLogado.getUsuario(), this.entityManager);

        this.mensageiroEmailService.envia(pergunta);

        this.entityManager.merge(pergunta.getProduto());

    }
}

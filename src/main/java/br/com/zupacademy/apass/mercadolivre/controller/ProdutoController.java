package br.com.zupacademy.apass.mercadolivre.controller;

import br.com.zupacademy.apass.mercadolivre.dto.request.NovoProdutoRequest;
import br.com.zupacademy.apass.mercadolivre.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {

    @Autowired
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public void cadastra(@RequestBody @Valid NovoProdutoRequest novoProdutoRequest, @AuthenticationPrincipal User user) {
        this.entityManager.persist(novoProdutoRequest.converte(user.getUsuario(), this.entityManager));
    }
}

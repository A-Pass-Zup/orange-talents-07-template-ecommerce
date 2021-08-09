package br.com.zupacademy.apass.mercadolivre.controller;

import br.com.zupacademy.apass.mercadolivre.dto.request.NovaOpniaoProdutoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoOpniaoController {

    @Autowired
    private EntityManager entityManager;

    @PostMapping("/{produtoId}/opniao")
    @Transactional
    private void cadastra(@RequestBody @Valid NovaOpniaoProdutoRequest novaOpniaoProdutoRequest) {

    }
}

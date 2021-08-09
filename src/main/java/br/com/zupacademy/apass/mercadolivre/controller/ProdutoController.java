package br.com.zupacademy.apass.mercadolivre.controller;

import br.com.zupacademy.apass.mercadolivre.dto.request.NovoProdutoRequest;
import br.com.zupacademy.apass.mercadolivre.security.User;
import br.com.zupacademy.apass.mercadolivre.validation.NaoPermiteCaracteristicasIguaisValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private NaoPermiteCaracteristicasIguaisValidator naoPermiteCaracteristicasIguaisValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(this.naoPermiteCaracteristicasIguaisValidator);
    }

    @PostMapping
    @Transactional
    public void cadastra(@RequestBody @Valid NovoProdutoRequest novoProdutoRequest, @AuthenticationPrincipal User user) {
        this.entityManager.persist(novoProdutoRequest.converte(user.getUsuario(), this.entityManager));
    }
}

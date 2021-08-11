package br.com.zupacademy.apass.mercadolivre.compra;

import br.com.zupacademy.apass.mercadolivre.email.EscritorEmailNovaCompra;
import br.com.zupacademy.apass.mercadolivre.security.User;
import br.com.zupacademy.apass.mercadolivre.service.MensageiroEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/compra")
public class CompraController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MensageiroEmailService mensageiroEmailService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> realizaCompra(@RequestBody @Valid NovaCompraRequest novaCompraRequest,
                                        UriComponentsBuilder uriComponentsBuilder,
                                        @AuthenticationPrincipal User usuarioLogado) {

        var compra = novaCompraRequest.converte(usuarioLogado.getUsuario(), this.entityManager);

        try {

            String uriRetorno = compra.processa(uriComponentsBuilder.toUriString());

            this.entityManager.persist(compra);

            this.mensageiroEmailService.envia(new EscritorEmailNovaCompra(compra));

            return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, uriRetorno).build();

        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }
}

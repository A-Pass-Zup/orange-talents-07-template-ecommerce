package br.com.zupacademy.apass.mercadolivre.controller;

import br.com.zupacademy.apass.mercadolivre.dto.request.AutenticacaoRequest;
import br.com.zupacademy.apass.mercadolivre.security.token.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtManager jwtManager;

    @PostMapping
    public ResponseEntity<String> autentica(@RequestBody @Valid AutenticacaoRequest autenticacaoRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = autenticacaoRequest.paraAuthenticationToken();
        try {
            return ResponseEntity.ok(
                    this.jwtManager.generateToken(
                            this.authenticationManager.authenticate(authenticationToken)));

        } catch (AuthenticationException exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}

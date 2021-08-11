package br.com.zupacademy.apass.mercadolivre.service;


import br.com.zupacademy.apass.mercadolivre.email.EscritorDeEmail;
import br.com.zupacademy.apass.mercadolivre.model.entity.ProdutoPergunta;

public interface MensageiroEmailService {
    void envia(EscritorDeEmail escritorDeEmail);
}

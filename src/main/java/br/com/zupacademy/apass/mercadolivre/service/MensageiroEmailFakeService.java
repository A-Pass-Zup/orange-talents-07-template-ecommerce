package br.com.zupacademy.apass.mercadolivre.service;

import br.com.zupacademy.apass.mercadolivre.email.EscritorDeEmail;
import br.com.zupacademy.apass.mercadolivre.model.entity.Produto;
import br.com.zupacademy.apass.mercadolivre.model.entity.ProdutoPergunta;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

@Service
@Profile("dev")
public class MensageiroEmailFakeService implements MensageiroEmailService {

    @Override
    public void envia(EscritorDeEmail escritorDeEmail) {
        System.out.println(escritorDeEmail.escreve());
    }
}

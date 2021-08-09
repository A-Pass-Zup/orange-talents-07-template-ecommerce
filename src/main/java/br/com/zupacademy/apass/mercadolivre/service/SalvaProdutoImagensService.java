package br.com.zupacademy.apass.mercadolivre.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface SalvaProdutoImagensService {
    Set<String> salva(Set<MultipartFile> imagens);
}

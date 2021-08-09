package br.com.zupacademy.apass.mercadolivre.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Profile("dev")
@Service
public class SalvaProdutoImagemFakeService implements SalvaProdutoImagensService {

    @Override
    public Set<String> salva(Set<MultipartFile> imagens) {
        return imagens.stream().map(img -> "https://repositorio.imagens/" + UUID.randomUUID() + "-"  + img.getOriginalFilename()).collect(Collectors.toSet());
    }
}

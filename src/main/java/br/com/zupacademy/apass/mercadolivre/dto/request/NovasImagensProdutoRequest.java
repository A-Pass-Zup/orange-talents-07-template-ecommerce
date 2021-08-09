package br.com.zupacademy.apass.mercadolivre.dto.request;

import br.com.zupacademy.apass.mercadolivre.model.entity.Produto;
import br.com.zupacademy.apass.mercadolivre.validation.constraints.ExistsId;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class NovasImagensProdutoRequest {

    @NotNull
    @ExistsId(domainClass = Produto.class, fieldId = "id")
    private Long produtoId;

    @NotNull
    @Size(min=1)
    private Set<MultipartFile> imagens;

    public NovasImagensProdutoRequest(@PathVariable @NotNull Long produtoId, @NotNull @Size(min=1) Set<MultipartFile> imagens) {
        this.produtoId = produtoId;
        this.imagens = imagens;
    }

    public Produto getProduto(EntityManager entityManager) {
        return entityManager.find(Produto.class, this.produtoId);
    }

    public Set<MultipartFile> getImagens() {
        return this.imagens;
    }
}

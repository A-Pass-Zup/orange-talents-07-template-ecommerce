package br.com.zupacademy.apass.mercadolivre.repository;

import br.com.zupacademy.apass.mercadolivre.model.entity.Produto;
import br.com.zupacademy.apass.mercadolivre.model.entity.ProdutoPergunta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoPerguntaRepository extends JpaRepository<ProdutoPergunta, Long> {
    Page<ProdutoPergunta> getByProduto(Produto produto, Pageable pageable);
}

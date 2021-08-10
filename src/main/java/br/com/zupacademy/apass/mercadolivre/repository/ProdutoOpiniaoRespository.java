package br.com.zupacademy.apass.mercadolivre.repository;

import br.com.zupacademy.apass.mercadolivre.model.entity.Produto;
import br.com.zupacademy.apass.mercadolivre.model.entity.ProdutoOpiniao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoOpiniaoRespository extends JpaRepository<ProdutoOpiniao, Long> {
    Page<ProdutoOpiniao> getByProduto(Produto produto, Pageable pageable);

    @Query("SELECT AVG(nota) FROM ProdutoOpiniao WHERE produto = :produto")
    double mediaDeNotasDoProduto(Produto produto);
}

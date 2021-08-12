package br.com.zupacademy.apass.mercadolivre.compra;

import br.com.zupacademy.apass.mercadolivre.compra.pagamento.GatewayPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompraRepository extends JpaRepository<Compra, Long> {

    Optional<Compra> findByGatewayPagamentoAndIdentificador(GatewayPagamento gatewayPagamento, String identificador);
}

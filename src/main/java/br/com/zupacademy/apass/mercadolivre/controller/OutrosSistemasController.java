package br.com.zupacademy.apass.mercadolivre.controller;

import br.com.zupacademy.apass.mercadolivre.compra.pagamento.NotaFiscalRequest;
import br.com.zupacademy.apass.mercadolivre.compra.pagamento.RankingVendedoresRequest;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/outros-sistemas")
public class OutrosSistemasController {

    @PostMapping("/nota-fiscal")
    public String notaFiscal(@Valid @RequestBody NotaFiscalRequest notaFiscalRequest) {
        System.out.println("Processando nota fiscal para pagamento");

        return "Nota fisacal gerada com sucesso: \n" +
                "Compra:" + notaFiscalRequest.getIdCompra() + '\n' +
                "Comprador" + notaFiscalRequest.getIdComprador() + '\n';
    }

    @PostMapping("/ranking-vendedores")
    public String rankinVendedores(@Valid @RequestBody RankingVendedoresRequest rankingVendedoresRequest) {
        System.out.println("Processando ranking do vendedor!");

        return "Vendedor: " + rankingVendedoresRequest.getIdVendedor() + '\n' +
                "Compra: " + rankingVendedoresRequest.getIdCompra() + '\n';
    }

}

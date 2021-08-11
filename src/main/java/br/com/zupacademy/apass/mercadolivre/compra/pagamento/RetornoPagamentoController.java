package br.com.zupacademy.apass.mercadolivre.compra.pagamento;

import br.com.zupacademy.apass.mercadolivre.compra.CompraRepository;
import br.com.zupacademy.apass.mercadolivre.email.EscritorEmailNovaCompra;
import br.com.zupacademy.apass.mercadolivre.service.MensageiroEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/retorno-pagamento")
public class RetornoPagamentoController {

    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private MensageiroEmailService emailService;
    @Autowired
    private NotaFiscalService notaFiscalService;
    @Autowired
    private RankingVendedoresService rankingVendedoresService;

    @PostMapping("/{gatewayPagamento}/{identificadorCompra}")
    public StatusPagamentoResponse processaPagamento(@PathVariable String identificadorCompra,
                                  @PathVariable String gatewayPagamento,
                                  @RequestBody @Valid ResultadoPagamentoResquest pagamentoRequest,
                                  UriComponentsBuilder uriComponentsBuilder) {

        var compra = this.compraRepository.findByIdentificador(identificadorCompra)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A compra com identificação '" + identificadorCompra + "' não foi encontrada!"));

        final var pagamento = pagamentoRequest.convert(compra, GatewayPagamento.valueOf(gatewayPagamento));

        try {
            pagamento.processa();
        } catch(IllegalCallerException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

        if(pagamento.sucesso()) {
            this.emailService.envia(new EscritorEmailCompraSucesso(pagamento));
            this.notaFiscalService.processa(pagamento);
            this.rankingVendedoresService.proccessa(pagamento);
        } else {
            this.emailService.envia(new EscritorEmailFalhaPagamento(pagamento, uriComponentsBuilder.toUriString()));
        }

        return new StatusPagamentoResponse(pagamento);

    }

}

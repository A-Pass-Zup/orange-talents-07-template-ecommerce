package br.com.zupacademy.apass.mercadolivre.email;

import br.com.zupacademy.apass.mercadolivre.model.entity.ProdutoPergunta;

import java.time.format.DateTimeFormatter;

public class EscritorEmailNovaProdutoPergunta implements EscritorDeEmail {

    private ProdutoPergunta pergunta;

    public EscritorEmailNovaProdutoPergunta(ProdutoPergunta pergunta) {
        this.pergunta = pergunta;
    }

    @Override
    public String escreve() {
        return "Para: " + pergunta.getProduto().getUsuarioCadastro().getLogin() + '\n'
                + "O usuario '" + pergunta.getUsuario().getLogin() + "' fez a seguinte pergunta para o produto '"
                + pergunta.getProduto().getNome() + "': " + pergunta.getTitulo() + " | " + pergunta.getDataHoraCadastro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

    }
}

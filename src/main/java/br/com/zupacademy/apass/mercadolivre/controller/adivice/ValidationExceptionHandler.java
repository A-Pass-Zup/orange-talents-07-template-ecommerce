package br.com.zupacademy.apass.mercadolivre.controller.adivice;

import br.com.zupacademy.apass.mercadolivre.dto.response.ErroValidacaoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErroValidacaoResponse> methodArgumentNotFoundExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {

        List<ErroValidacaoResponse> erros = new ArrayList<>();
        methodArgumentNotValidException.getFieldErrors().forEach( e -> {
            erros.add(new ErroValidacaoResponse(e.getField(), e.getDefaultMessage()));
        });

        return erros;
    }
}

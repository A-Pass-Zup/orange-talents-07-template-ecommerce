package br.com.zupacademy.apass.mercadolivre.controller.advice;

import br.com.zupacademy.apass.mercadolivre.dto.response.ErroValidacaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErroValidacaoResponse> validationBindException(BindException validationBindException) {

        List<ErroValidacaoResponse> erros = new ArrayList<>();
        validationBindException.getFieldErrors().forEach( e -> {
            erros.add(new ErroValidacaoResponse(e.getField(), String.valueOf(e.getRejectedValue()) ,this.messageSource.getMessage(e, LocaleContextHolder.getLocale())));
        });

        return erros;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return "Valor" + exception.getMostSpecificCause().getMessage();
    }

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<?> responseStatusException(ResponseStatusException responseStatusException) {
        return ResponseEntity.status(responseStatusException.getStatus()).body(responseStatusException.getReason());
    }
}

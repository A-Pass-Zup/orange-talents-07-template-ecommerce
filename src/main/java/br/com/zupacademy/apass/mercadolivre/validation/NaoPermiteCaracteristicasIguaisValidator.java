package br.com.zupacademy.apass.mercadolivre.validation;

import br.com.zupacademy.apass.mercadolivre.dto.request.NovoProdutoRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NaoPermiteCaracteristicasIguaisValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(NovoProdutoRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        var novoProdutoRequest = (NovoProdutoRequest) target;

        if(novoProdutoRequest.existeCaracteristicaDuplicada()) {
            errors.rejectValue("caracteristicas", "NaoPermiteCaractericasDucplicada");
        }

    }
}

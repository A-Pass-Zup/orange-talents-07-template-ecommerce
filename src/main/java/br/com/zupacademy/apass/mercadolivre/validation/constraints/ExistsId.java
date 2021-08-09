package br.com.zupacademy.apass.mercadolivre.validation.constraints;

import br.com.zupacademy.apass.mercadolivre.validation.ExistsIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistsIdValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsId {
    String message() default "{br.com.zupacademy.apass.validation.constraint.existsid}";

    Class[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldId();

    Class<?> domainClass();

    boolean nullable() default false;
}

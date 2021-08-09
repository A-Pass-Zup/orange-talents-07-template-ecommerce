package br.com.zupacademy.apass.mercadolivre.validation;

import br.com.zupacademy.apass.mercadolivre.validation.constraints.ExistsId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Number> {

    private Class<?> domainClass;
    private boolean nullable;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MessageSource messageSource;


    @Override
    public void initialize(ExistsId existsId) {
        this.nullable = existsId.nullable();
        this.domainClass = existsId.domainClass();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext constraintValidatorContext) {

        if(value == null) {
            return this.nullable;
        }

        List<Integer> list = entityManager.createQuery("SELECT 1 FROM " + this.domainClass.getName() + " WHERE id =:pId", Integer.class)
                .setParameter("pId", value)
                .getResultList();

        if(list.size() > 1) {
            throw new IllegalStateException(
                    this.messageSource.getMessage("ExistsId.IllegalState",
                            new Object[] { this.domainClass.getName(), "Id" , value},
                            LocaleContextHolder.getLocale()
                    )
            );
        }

        return !list.isEmpty();
    }

}

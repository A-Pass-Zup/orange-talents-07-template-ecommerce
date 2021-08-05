package br.com.zupacademy.apass.mercadolivre.validation;

import br.com.zupacademy.apass.mercadolivre.validation.constraints.UniqueValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;


public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String fieldName;
    
    private Class<?> domainClass;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MessageSource messageSource;

    @Override
    public void initialize(UniqueValue uniqueValue) {
        this.fieldName = uniqueValue.fieldName();
        this.domainClass = uniqueValue.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        List<?> list = entityManager
                .createQuery("SELECT 1 FROM " + this.domainClass.getName() + " WHERE " + this.fieldName + " = :pValue", Integer.class)
                .setParameter("pValue", value)
                .getResultList();

        if(list.size() > 1) {
            throw new IllegalStateException(
                    this.messageSource.getMessage("UniqueValue.IllegalState",
                            new Object[] { this.domainClass.getName(), this.fieldName, value},
                            LocaleContextHolder.getLocale()
                    )
            );
        }

        return list.isEmpty();
    }
}

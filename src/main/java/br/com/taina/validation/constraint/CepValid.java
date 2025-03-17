package br.com.taina.validation.constraint;

import br.com.taina.validation.CepValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CepValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CepValid {

    String message() default "Erro! O campo cep está inválido. Formato aceito : Apenas números : XXXXXXXX ou XXXXX-XXX.";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default{};
}

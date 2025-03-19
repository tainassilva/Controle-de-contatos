package br.com.taina.validation.constraint;

import br.com.taina.validation.UFValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UFValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface UFValid {

    String message() default "Erro! O campo UF está inválido. Digite um estado brasileiro válido. Exemplo: SP";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default{};
}

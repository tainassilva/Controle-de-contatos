package br.com.taina.validation.constraint;

import br.com.taina.validation.TipoContatoValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TipoContatoValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TipoContatoValid {

    String message() default "Erro! Tipo de contato inválido." +
            " Insira um tipo de contato válido: TELEFONE, CELULAR, EMAIL ou LINKEDIN";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default{};
}

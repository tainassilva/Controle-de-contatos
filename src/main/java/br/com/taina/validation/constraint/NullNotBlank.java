package br.com.taina.validation.constraint;

import br.com.taina.validation.NullNotBlankValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NullNotBlankValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface NullNotBlank {

    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default{};
}

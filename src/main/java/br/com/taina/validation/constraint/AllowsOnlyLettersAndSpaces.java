package br.com.taina.validation.constraint;

import br.com.taina.validation.AllowsOnlyLettersAndSpacesValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AllowsOnlyLettersAndSpacesValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowsOnlyLettersAndSpaces {

    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default{};
}

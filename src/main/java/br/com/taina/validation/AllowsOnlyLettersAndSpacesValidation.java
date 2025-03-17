package br.com.taina.validation;

import br.com.taina.validation.constraint.AllowsOnlyLettersAndSpaces;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AllowsOnlyLettersAndSpacesValidation implements ConstraintValidator<AllowsOnlyLettersAndSpaces, String> {

    @Override
    public void initialize(AllowsOnlyLettersAndSpaces constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;  // Permitindo null, caso a anotação não exija o campo não nulo
        }

        // Expressão regular que permite letras e espaços
        return value.matches("^[A-Za-záàãâéèêíóôúçÁÀÂÉÊÍÓÔÚÇ\\s]+$");
    }
}

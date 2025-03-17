package br.com.taina.validation;

import br.com.taina.enums.Estados;
import br.com.taina.validation.constraint.UFValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class UFValidation implements ConstraintValidator<UFValid, String> {

    @Override
    public void initialize(UFValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        List<Estados> estados = List.of(Estados.values());

        if (value != null) {
            try {
                return estados.contains(Estados.valueOf(value.toUpperCase()));
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
        return true;
    }
}

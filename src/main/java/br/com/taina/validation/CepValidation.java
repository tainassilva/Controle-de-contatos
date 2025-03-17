package br.com.taina.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import br.com.taina.validation.constraint.CepValid;

public class CepValidation implements ConstraintValidator<CepValid, String> {

    @Override
    public void initialize(CepValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        // Lógica para validar o CEP
        return value.matches("\\d{5}-?\\d{3}");  // Exemplo de regex para validar CEP (XXXXX-XXX ou XXXXXXXX)
    }
}

package br.com.taina.validation;

import br.com.taina.enums.Estados;
import br.com.taina.enums.TipoContato;
import br.com.taina.validation.constraint.TipoContatoValid;
import br.com.taina.validation.constraint.UFValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class TipoContatoValidation implements ConstraintValidator<TipoContatoValid,String> {

    @Override
    public void initialize(TipoContatoValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        List<TipoContato> tipoContato = List.of(TipoContato.values());

        if (value != null) {
            try {
                return tipoContato.contains(TipoContato.valueOf(value.toUpperCase()));
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
        return true;
    }
}

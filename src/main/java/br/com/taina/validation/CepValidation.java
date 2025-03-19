package br.com.taina.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import br.com.taina.validation.constraint.CepValid;

/**
 * Validador customizado para a anotação {@link CepValid}.
 *
 * Esta classe é responsável por validar se o valor de uma string corresponde ao formato de um CEP (Código de Endereçamento
 * Postal) brasileiro. A validação é realizada utilizando uma expressão regular que aceita um formato de CEP com ou sem
 * o hífen separador.
 *
 * A anotação é útil para validar campos de CEP em formulários, garantindo que o valor informado esteja em um formato
 * correto de CEP brasileiro (XXXXX-XXX ou XXXXXXXX).
 *
 * @see CepValid
 */
public class CepValidation implements ConstraintValidator<CepValid, String> {

    @Override
    public void initialize(CepValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null) {
            return value.matches("\\d{5}-?\\d{3}");
        }
        return true;
    }
}

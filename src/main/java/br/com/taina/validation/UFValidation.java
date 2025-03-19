package br.com.taina.validation;

import br.com.taina.enums.Estados;
import br.com.taina.validation.constraint.UFValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

/**
 * Classe responsável por validar o campo de Unidade Federativa (UF) em uma String.
 * Esta classe implementa a interface {@link ConstraintValidator}, que valida o valor de uma String
 * de acordo com a anotação personalizada {@link UFValid}.
 *
 * A validação verifica se o valor fornecido corresponde a uma Unidade Federativa (UF) válida,
 * conforme definido na enumeração {@link Estados}.
 *
 * A anotação {@link UFValid} pode ser usada para garantir que o valor da UF seja um código de estado válido.
 *
 * @see UFValid
 */
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
                // Verifica se o valor fornecido corresponde a uma UF válida da enum Estados.
                return estados.contains(Estados.valueOf(value.toUpperCase()));
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
        return true;
    }
}

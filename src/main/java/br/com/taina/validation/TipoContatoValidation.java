package br.com.taina.validation;

import br.com.taina.enums.TipoContato;
import br.com.taina.validation.constraint.TipoContatoValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

/**
 * Classe responsável por validar o campo de tipo de contato em uma String.
 * Esta classe implementa a interface {@link ConstraintValidator}, que valida o valor de uma String
 * de acordo com a anotação personalizada {@link TipoContatoValid}.
 *
 * A validação verifica se o valor fornecido corresponde a um dos tipos de contato definidos na enumeração {@link TipoContato}.
 *
 * A anotação {@link TipoContatoValid} pode ser utilizada para garantir que o valor de tipo de contato
 * esteja dentro dos tipos permitidos definidos na enumeração.

 * @see TipoContatoValid
 */
public class TipoContatoValidation implements ConstraintValidator<TipoContatoValid, String> {

    @Override
    public void initialize(TipoContatoValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        List<TipoContato> tipoContato = List.of(TipoContato.values());

        if (value != null) {
            try {
                // Verifica se o valor fornecido corresponde a um dos tipos de contato definidos na enum.
                return tipoContato.contains(TipoContato.valueOf(value.toUpperCase()));
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
        return true;
    }
}

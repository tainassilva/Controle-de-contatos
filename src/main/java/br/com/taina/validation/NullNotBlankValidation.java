package br.com.taina.validation;

import br.com.taina.validation.constraint.NullNotBlank;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Classe responsável por validar se um campo de String não é nulo e não é em branco.
 * Esta classe implementa a interface {@link ConstraintValidator}, que valida o valor de uma String
 * de acordo com a anotação personalizada {@link NullNotBlank}.
 *
 * A validação permite que o campo seja nulo, mas, se não for, ele não pode estar em branco.
 *
 * A anotação {@link NullNotBlank} pode ser usada em qualquer campo de String onde se deseja
 * garantir que, se o valor for fornecido, ele não pode ser vazio ou composto apenas por espaços.
 *
 * @see NullNotBlank
 */
public class NullNotBlankValidation implements ConstraintValidator<NullNotBlank, String> {


    @Override
    public void initialize(NullNotBlank constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null) {
            return !value.isBlank();  // Retorna false se a String estiver em branco.
        }
        return true;  // Considera válido se o valor for nulo.
    }
}

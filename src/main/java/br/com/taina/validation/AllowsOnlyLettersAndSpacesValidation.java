package br.com.taina.validation;

import br.com.taina.validation.constraint.AllowsOnlyLettersAndSpaces;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validador customizado para a anotação {@link AllowsOnlyLettersAndSpaces}.
 *
 * Esta classe é responsável por validar se o valor de uma string contém apenas letras (maiúsculas e minúsculas),
 * incluindo caracteres acentuados, e espaços em branco. A validação é feita por meio de uma expressão regular
 * que permite letras e espaços, mas impede números e outros caracteres especiais.
 *
 * A anotação é útil em casos em que você deseja garantir que o valor de um campo seja composto apenas por letras
 * e espaços, como para nomes de pessoas, por exemplo.
 *
 * A validação é realizada quando a anotação {@link AllowsOnlyLettersAndSpaces} é aplicada em um campo do tipo String.
 *
 * @see AllowsOnlyLettersAndSpaces
 */
public class AllowsOnlyLettersAndSpacesValidation implements ConstraintValidator<AllowsOnlyLettersAndSpaces, String> {


    @Override
    public void initialize(AllowsOnlyLettersAndSpaces constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null) {
            return value.matches("^[A-Za-záàãâéèêíóôúçÁÀÂÉÊÍÓÔÚÇ\\s]+$");
        }
        return true;
    }
}

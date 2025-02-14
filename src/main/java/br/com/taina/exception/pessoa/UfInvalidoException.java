package br.com.taina.exception.pessoa;

/**
 * Exceção personalizada lançada quando os Enums da classe 
 * {@link br.com.taina.enums.Estados} contem um estado que não corresponde a nenhum enum.
 * A validação dos Enums é feito na classe {@link br.com.taina.validation.contato.ContatoValidation}.
 */
@SuppressWarnings("serial")
public class UfInvalidoException extends RuntimeException {
	
	/**
     * Construtor da exceção, que recebe uma mensagem personalizada para ser exibida.
     *
     * @param message Mensagem de erro que descreve o motivo da exceção.
     */
    public UfInvalidoException(String message) {
        super(message);
    }
}

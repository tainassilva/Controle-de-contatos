package br.com.taina.exception.contato;


/**
 * Exceção personalizada lançada quando o valor do Enum EMAIL na classe 
 * {@link br.com.taina.enums.TipoContato} contem um formato de e-mail inválido.
 * A validação do formato é feito na classe {@link br.com.taina.validation.contato.ContatoValidation}.
 */

@SuppressWarnings("serial")
public class EmailFormatoInvalidoException extends RuntimeException{

	 /**
     * Construtor da exceção, que recebe uma mensagem personalizada para ser exibida.
     *
     * @param message Mensagem de erro que descreve o motivo da exceção.
     */
	public EmailFormatoInvalidoException(String message) {
		super(message);
	}
	
}

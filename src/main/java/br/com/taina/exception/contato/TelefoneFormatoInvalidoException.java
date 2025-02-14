package br.com.taina.exception.contato;

/**
 * Exceção personalizada lançada quando o valor do Enum TELEFONE_FIXO ou CELULAR na classe 
 * {@link br.com.taina.enums.TipoContato} contem um formato de telefone inválido.
 * A validação do formato é feito na classe {@link br.com.taina.validation.ContatoValidationn}.
 */
@SuppressWarnings("serial")
public class TelefoneFormatoInvalidoException extends RuntimeException{

	/**
     * Construtor da exceção, que recebe uma mensagem personalizada para ser exibida.
     *
     * @param message Mensagem de erro que descreve o motivo da exceção.
     */
	public TelefoneFormatoInvalidoException(String message) {
		super(message);
	}
}

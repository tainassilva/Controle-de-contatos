package br.com.taina.exception.contato;

/**
 * Essa exceção é lançada quando uma pessoa não tiver nenhum contato associado
 * 
 */
@SuppressWarnings("serial")
public class SemContatoAssociadoException extends RuntimeException{

	 /**
     * Construtor da exceção, que recebe uma mensagem personalizada para ser exibida.
     *
     * @param message Mensagem de erro que descreve o motivo da exceção.
     */
	public SemContatoAssociadoException(String message) {
		super(message);
	}

}

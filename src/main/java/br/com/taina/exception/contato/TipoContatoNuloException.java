package br.com.taina.exception.contato;

/**
 * Esta exceção é utilizada para sinalizar falhas na validação de tipo de contato na classe 
 * {@link br.com.taina.validation.contato.ContatoValidation}, que quando um enum
 * tipo de contato fornecido é null, lança esta exceção, o que viola os parâmetros de validação do método 
 * {@link ValidarContato}
 */
@SuppressWarnings("serial")
public class TipoContatoNuloException extends RuntimeException{

	/**
     * Construtor da exceção, que recebe uma mensagem personalizada para ser exibida.
     *
     * @param message Mensagem de erro que descreve o motivo da exceção.
     */
	public TipoContatoNuloException(String message) {
		super(message);
	}

}

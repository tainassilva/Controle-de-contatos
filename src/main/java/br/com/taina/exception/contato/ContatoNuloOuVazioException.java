package br.com.taina.exception.contato;

/**
 * Esta exceção personalizada é utilizada para sinalizar falhas na validação de contatos na classe 
 * {@link br.com.taina.validation.contato.ContatoValidation}, que quando o objeto 
 * contato fornecido é nulo ou vazio, o que viola os parâmetros de validação do método 
 * {@link ValidarContato}
 */

@SuppressWarnings("serial")
public class ContatoNuloOuVazioException extends RuntimeException{

    /**
     * Construtor da exceção, que recebe uma mensagem personalizada para ser exibida.
     *
     * @param message Mensagem de erro que descreve o motivo da exceção.
     */
	public ContatoNuloOuVazioException(String message) {
		super(message);
	}
	
}	

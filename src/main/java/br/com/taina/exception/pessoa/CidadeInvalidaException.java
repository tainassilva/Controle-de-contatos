package br.com.taina.exception.pessoa;

/**
 * Exceção personalizada lançada quando a cidade da entidade 
 * {@link br.com.taina.model.Pessoa} recebe um formato inválido.
 * A validação do formato é feito na classe {@link br.com.taina.validation.pessoa.PessoaValidation}.
 */
@SuppressWarnings("serial")
public class CidadeInvalidaException extends RuntimeException {
	
	 /**
     * Construtor da exceção, que recebe uma mensagem personalizada para ser exibida.
     *
     * @param message Mensagem de erro que descreve o motivo da exceção.
     */
    public CidadeInvalidaException(String message) {
        super(message);
    }
    
}
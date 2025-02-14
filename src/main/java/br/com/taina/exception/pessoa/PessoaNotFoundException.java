package br.com.taina.exception.pessoa;

/**
 * Esta exceção é usada para indicar falhas na validação de contatos dentro da classe 
 * {@link br.com.taina.validation.pessoa.PessoaValidation} quando não encontra
 * a pessoa sinalizado nos parâmetros do método {@link ValidarPessoa}
 */
@SuppressWarnings("serial")
public class PessoaNotFoundException extends RuntimeException {

	/**
     * Construtor da exceção, que recebe uma mensagem personalizada para ser exibida.
     *
     * @param message Mensagem de erro que descreve o motivo da exceção.
     */
	public PessoaNotFoundException(String mensagem) {
		super(mensagem);
	}
}

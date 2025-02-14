package br.com.taina.exception.pessoa;

/**
 * Esta exceção personalizada é utilizada para sinalizar falhas na validação de contatos na classe 
 * {@link br.com.taina.validation.pessoa.PessoaValidation}, que quando o nome do objeto pessoa
 * fornecido é nulo ou vazio, o que viola os parâmetros de validação do método 
 * {@link ValidarPessoa}
 */
@SuppressWarnings("serial")
public class NomeNuloOuVazioException extends RuntimeException{

	public NomeNuloOuVazioException(String message) {
		super(message);
	}
	

}

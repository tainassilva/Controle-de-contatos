package br.com.taina.exception.pessoa;


@SuppressWarnings("serial")
public class PessoaNotFoundException extends RuntimeException {

	public PessoaNotFoundException(String mensagem) {
		super(mensagem);
	}

}

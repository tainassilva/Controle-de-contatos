package br.com.taina.exception.contato;

@SuppressWarnings("serial")
public class SemContatoAssociadoException extends RuntimeException{

	public SemContatoAssociadoException(String message) {
		super(message);
	}

}

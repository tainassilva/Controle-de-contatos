package br.com.taina.exception.contato;

@SuppressWarnings("serial")
public class EmailFormatoInvalidoException extends RuntimeException{

	public EmailFormatoInvalidoException(String message) {
		super(message);
	}
	
}

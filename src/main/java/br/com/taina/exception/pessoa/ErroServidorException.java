package br.com.taina.exception.pessoa;

@SuppressWarnings("serial")
public class ErroServidorException extends RuntimeException {

	public ErroServidorException(String message) {
		super(message);
	}
	
}

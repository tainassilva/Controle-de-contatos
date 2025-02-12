package br.com.taina.exception.contato;

@SuppressWarnings("serial")
public class ContatoNotFoundException extends RuntimeException{

	public ContatoNotFoundException(String message) {
		super(message);
	}
}

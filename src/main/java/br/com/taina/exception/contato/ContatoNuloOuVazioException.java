package br.com.taina.exception.contato;

@SuppressWarnings("serial")
public class ContatoNuloOuVazioException extends RuntimeException{

	public ContatoNuloOuVazioException(String message) {
		super(message);
	}
	
}	

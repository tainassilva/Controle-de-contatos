package br.com.taina.exception.pessoa;

@SuppressWarnings("serial")
public class NomeNuloOuVazioException extends RuntimeException{

	public NomeNuloOuVazioException(String message) {
		super(message);
	}
	

}

package br.com.taina.exception;

@SuppressWarnings("serial")
public class CampoVazioException extends RuntimeException{

	public CampoVazioException(String message) {
		super("Erro! O campo não pode ser vazio.");
	}

}

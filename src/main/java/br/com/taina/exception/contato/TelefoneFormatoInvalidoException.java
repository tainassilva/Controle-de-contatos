package br.com.taina.exception.contato;

@SuppressWarnings("serial")
public class TelefoneFormatoInvalidoException extends RuntimeException{

	public TelefoneFormatoInvalidoException(String message) {
		super(message);
	}
}

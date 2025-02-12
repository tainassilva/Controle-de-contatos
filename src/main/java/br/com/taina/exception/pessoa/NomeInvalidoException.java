package br.com.taina.exception.pessoa;

@SuppressWarnings("serial")
public class NomeInvalidoException extends RuntimeException {
    public NomeInvalidoException(String message) {
        super(message);
    }
}


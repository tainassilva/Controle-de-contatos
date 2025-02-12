package br.com.taina.exception.pessoa;

@SuppressWarnings("serial")
public class CidadeInvalidaException extends RuntimeException {
    public CidadeInvalidaException(String message) {
        super(message);
    }
    
}
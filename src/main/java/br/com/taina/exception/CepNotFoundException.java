package br.com.taina.exception;


// Exceção para quando o CEP não for encontrado
public class CepNotFoundException extends RuntimeException {
    public CepNotFoundException(String message) {
        super(message);
    }
}

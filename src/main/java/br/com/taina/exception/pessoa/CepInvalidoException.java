package br.com.taina.exception.pessoa;


// Exceção para quando o CEP não for encontrado
@SuppressWarnings("serial")
public class CepInvalidoException extends RuntimeException {
    public CepInvalidoException(String message) {
        super(message);
    }
}

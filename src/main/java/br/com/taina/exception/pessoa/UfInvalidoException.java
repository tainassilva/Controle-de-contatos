package br.com.taina.exception.pessoa;

// Exceção para quando os dados de entrada são inválidos (como nome vazio)
@SuppressWarnings("serial")
public class UfInvalidoException extends RuntimeException {
    public UfInvalidoException(String message) {
        super(message);
    }
}

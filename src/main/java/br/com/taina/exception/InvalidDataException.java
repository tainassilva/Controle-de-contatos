package br.com.taina.exception;

// Exceção para quando os dados de entrada são inválidos (como nome vazio)
public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super(message);
    }
}

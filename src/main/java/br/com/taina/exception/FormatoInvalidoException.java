package br.com.taina.exception;

@SuppressWarnings("serial")
/**
 * Exceção personalizada lançada quando um formato de dado fornecido é considerado inválido.
 */
public class FormatoInvalidoException extends RuntimeException {

    /**
     * Construtor para a exceção FormatoInvalidoException.
     * @param message A mensagem de erro que descreve o motivo do formato inválido.
     */
    public FormatoInvalidoException(String message) {
        super(message);
    }
}

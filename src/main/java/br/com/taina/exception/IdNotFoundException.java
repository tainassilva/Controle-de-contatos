package br.com.taina.exception;

/**
 * Exceção personalizada lançada quando um ID não é encontrado no sistema.
 */
public class IdNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * @param message A mensagem de erro que descreve o motivo do ID não ter sido encontrado.
     */
    public IdNotFoundException(String message) {
        super(message);
    }
}

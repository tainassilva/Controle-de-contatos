package br.com.taina.exception;

/**
 * Exceção personalizada lançada quando um valor obrigatório é encontrado como nulo.
 */
public class CampoNotNullException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
     * @param message A mensagem de erro que descreve o campo ou valor que não pode ser nulo.
     */
    public CampoNotNullException(String message) {
        super(message);
    }
}

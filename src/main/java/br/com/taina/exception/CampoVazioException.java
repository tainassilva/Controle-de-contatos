package br.com.taina.exception;

/**
 * Exceção personalizada lançada quando um campo obrigatório é encontrado vazio.
 */
public class CampoVazioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Recebe uma mensagem padrão de erro será utilizada:
     * "Erro! O campo não pode ser vazio."
     * 
     * @param message A mensagem de erro a ser associada a exceção.
     */
    public CampoVazioException(String message) {
        super("Erro! O campo não pode ser vazio.");
    }
}

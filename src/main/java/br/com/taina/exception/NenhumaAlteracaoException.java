package br.com.taina.exception;

/**
 * Exceção personalizada lançada quando não há alterações para serem atualizadas.
 */
public class NenhumaAlteracaoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
    * Recebe uma mensagem padrão de erro será utilizada:
    * "Não há alterações para atualizar."
    * 
    * @param message A mensagem de erro a ser associada a exceção.
    */
    public NenhumaAlteracaoException(String message) {
        super("Não há alterações para atualizar.");
    }
}

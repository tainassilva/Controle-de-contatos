package br.com.taina.exception;


/**
 * Exceção personalizada lançada quando ocorre um erro no servidor, como problemas no banco de dados
 * ou falhas inesperadas durante a execução de operações do sistema.
 * A exceção pode ser usada para capturar falhas gerais de servidor e fornecer uma resposta adequada.
 */
public class ErroServidorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	 /**
     * Recebe uma mensagem padrão de erro será utilizada:
     * "Erro no servidor."
     * 
     * @param message A mensagem de erro a ser associada a exceção.
     */
	public ErroServidorException(String message) {
        super("Erro no servidor.");
    }
}

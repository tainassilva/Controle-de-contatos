package br.com.taina.exception;


/**
 * Exceção personalizada lançada quando ocorre um erro no servidor, como problemas no banco de dados
 * ou falhas inesperadas durante a execução de operações do sistema.
 * A exceção pode ser usada para capturar falhas gerais de servidor e fornecer uma resposta adequada.
 */
@SuppressWarnings("serial")
public class ErroServidorException extends RuntimeException {

    /**
     * Construtor da exceção, que recebe uma mensagem personalizada para descrever o erro ocorrido.
     *
     * @param message Mensagem detalhada que explica o motivo da exceção. Esta mensagem será exibida
     *                quando a exceção for capturada e pode ser útil para diagnóstico ou debug.
     */
    public ErroServidorException(String message) {
        super("Erro no servidor");
    }
}

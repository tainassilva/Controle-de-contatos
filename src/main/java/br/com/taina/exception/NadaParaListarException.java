package br.com.taina.exception;

/**
 * Exceção personalizada lançada quando não há dados a serem listados.
 */
public class NadaParaListarException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    /**
     * @param message A mensagem de erro que descreve a razão pela qual não há dados para listar.
     */
    public NadaParaListarException(String message) {
        super(message);
    }
}

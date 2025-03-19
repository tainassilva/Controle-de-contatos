package br.com.taina.exception;

import java.io.Serial;

/**
 * Exceção personalizada lançada quando não há alterações para serem atualizadas.
 */

// Ver uma forma de implementar isso ...
public class NenhumaAlteracaoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NenhumaAlteracaoException(String message) {
        super("Não há alterações para atualizar.");
    }
}

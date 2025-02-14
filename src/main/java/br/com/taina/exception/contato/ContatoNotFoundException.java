package br.com.taina.exception.contato;

/**
 * Esta exceção é usada para indicar falhas na validação de contatos dentro da classe 
 * {@link br.com.taina.validation.contato.ContatoValidation} quando não encontra
 * o contato sinalizado nos parâmetros do método {@link ValidarContato}
 */
@SuppressWarnings("serial")
public class ContatoNotFoundException extends RuntimeException {

    /**
     * Construtor da exceção, que recebe uma mensagem personalizada para ser exibida.
     *
     * @param message Mensagem de erro que descreve o motivo da exceção.
     */
    public ContatoNotFoundException(String message) {
        super(message);
    }
}

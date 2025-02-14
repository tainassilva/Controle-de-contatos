package br.com.taina.validation.contato;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.taina.exception.contato.ContatoNotFoundException;
import br.com.taina.exception.contato.ContatoNuloOuVazioException;
import br.com.taina.exception.contato.EmailFormatoInvalidoException;
import br.com.taina.exception.contato.LinkedInFormatoInvalidoException;
import br.com.taina.exception.contato.SemContatoAssociadoException;
import br.com.taina.exception.contato.TelefoneFormatoInvalidoException;
import br.com.taina.exception.contato.TipoContatoInvalidoException;
import br.com.taina.exception.contato.TipoContatoNuloException;
import br.com.taina.validation.ErrorResponse;

/**
 * Classe responsável por manipular exceções relacionadas a contatos.
 * Captura as exceções específicas e retorna respostas HTTP apropriadas.
 */
@ControllerAdvice
public class ContatoHandler {

    /**
     * Manipula a exceção quando um contato não é encontrado.
     *
     * @param ex A exceção {@link ContatoNotFoundException} lançada quando o contato não existe.
     * @return Um {@link ResponseEntity} contendo o código de status 404 (Not Found) e uma mensagem de erro.
     */
    @ExceptionHandler(ContatoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleContatoNaoEncontrado(ContatoNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity.status(404).body(error);
    }

    /**
     * Manipula a exceção para formato inválido de telefone.
     *
     * @param ex A exceção {@link TelefoneFormatoInvalidoException} lançada quando o telefone tem um formato inválido.
     * @return Um {@link ResponseEntity} contendo o código de status 422 (Unprocessable Entity) e uma mensagem de erro.
     */
    @ExceptionHandler(TelefoneFormatoInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleTelefoneFormatoInvalido(TelefoneFormatoInvalidoException ex) {
        ErrorResponse error = new ErrorResponse(422, ex.getMessage());
        return ResponseEntity.unprocessableEntity().body(error);
    }

    /**
     * Manipula a exceção quando um contato é enviado nulo ou vazio.
     *
     * @param ex A exceção {@link ContatoNuloOuVazioException} lançada quando um contato obrigatório não é informado.
     * @return Um {@link ResponseEntity} contendo o código de status 400 (Bad Request) e uma mensagem de erro.
     */
    @ExceptionHandler(ContatoNuloOuVazioException.class)
    public ResponseEntity<ErrorResponse> handleContatoNuloOuVazio(ContatoNuloOuVazioException ex) {
        ErrorResponse error = new ErrorResponse(400, ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * Manipula a exceção para formato inválido de e-mail.
     *
     * @param ex A exceção {@link EmailFormatoInvalidoException} lançada quando o e-mail não segue um formato válido.
     * @return Um {@link ResponseEntity} contendo o código de status 422 (Unprocessable Entity) e uma mensagem de erro.
     */
    @ExceptionHandler(EmailFormatoInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleEmailFormatoInvalido(EmailFormatoInvalidoException ex) {
        ErrorResponse error = new ErrorResponse(422, ex.getMessage());
        return ResponseEntity.unprocessableEntity().body(error);
    }

    /**
     * Manipula a exceção quando o tipo de contato é nulo.
     *
     * @param ex A exceção {@link TipoContatoNuloException} lançada quando o tipo de contato não é informado.
     * @return Um {@link ResponseEntity} contendo o código de status 400 (Bad Request) e uma mensagem de erro.
     */
    @ExceptionHandler(TipoContatoNuloException.class)
    public ResponseEntity<ErrorResponse> handleContatoNulo(TipoContatoNuloException ex) {
        ErrorResponse error = new ErrorResponse(400, ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * Manipula a exceção quando uma pessoa não possui contatos associados.
     *
     * @param ex A exceção {@link SemContatoAssociadoException} lançada quando a pessoa não tem contatos cadastrados.
     * @return Um {@link ResponseEntity} com status 204 (No Content), indicando que a pessoa existe, mas não possui contatos.
     */
    @ExceptionHandler(SemContatoAssociadoException.class)
    public ResponseEntity<ErrorResponse> handleSemContatoAssociado(SemContatoAssociadoException ex) {
        ErrorResponse error = new ErrorResponse(204, ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
    
    /**
     * Manipula a exceção para formato inválido de linkedIn
     *
     * @param ex A exceção {@link LinkedInFormatoInvalidoException} lançada quando o linkedLn não segue um formato válido.
     * @return Um {@link ResponseEntity} contendo o código de status 422 (Unprocessable Entity) e uma mensagem de erro.
     */
    @ExceptionHandler(LinkedInFormatoInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleLinkedInFormatoInvalido(LinkedInFormatoInvalidoException ex) {
        ErrorResponse error = new ErrorResponse(422, ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
    
    /**
     * Manipula a exceção para caso o valor esteja fora do enum
     *
     * @param ex A exceção {@link LinkedInFormatoInvalidoException} lançada quando o linkedLn não segue um formato válido.
     * @return Um {@link ResponseEntity} contendo o código de status 404 (Unprocessable Entity) e uma mensagem de erro.
     */
    @ExceptionHandler(TipoContatoInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleTipoContatoInvalido(TipoContatoInvalidoException ex) {
        ErrorResponse error = new ErrorResponse(400, ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}

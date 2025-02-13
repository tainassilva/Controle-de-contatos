package br.com.taina.exception.contato;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.taina.exception.ErrorResponse;

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
        ErrorResponse error = new ErrorResponse(404, "Contato não encontrado: " + ex.getMessage());
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
        ErrorResponse error = new ErrorResponse(422, "Formato de telefone inválido: " + ex.getMessage());
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
        ErrorResponse error = new ErrorResponse(400, "O contato não pode ser nulo ou vazio: " + ex.getMessage());
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
        ErrorResponse error = new ErrorResponse(422, "Formato de e-mail inválido: " + ex.getMessage());
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
        ErrorResponse error = new ErrorResponse(400, "O tipo de contato não pode ser nulo: " + ex.getMessage());
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
        ErrorResponse error = new ErrorResponse(204, "Erro: " + ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
    
}
